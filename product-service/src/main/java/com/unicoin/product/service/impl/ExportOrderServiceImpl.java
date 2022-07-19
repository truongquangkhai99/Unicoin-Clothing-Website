package com.unicoin.product.service.impl;

import com.unicoin.amqp.RabbitMQMessageProducer;
import com.unicoin.clients.commons.RabbitKey;
import com.unicoin.clients.rabbitmqModel.QueueExportOrder;
import com.unicoin.clients.rabbitmqModel.QueueExportOrderDetail;
import com.unicoin.product.common.CommonsUtils;
import com.unicoin.product.dto.ExportOrderDTO;
import com.unicoin.product.dto.ExportOrderDetailDTO;
import com.unicoin.product.entity.ExportOrder;
import com.unicoin.product.entity.ExportOrderDetail;
import com.unicoin.product.ex.AppException;
import com.unicoin.product.ex.ExceptionCode;
import com.unicoin.product.form.AddExportOrderDetail;
import com.unicoin.product.form.CheckoutExportOrders;
import com.unicoin.product.repository.ExportOrderDetaiRepository;
import com.unicoin.product.repository.ExportOrderRepository;
import com.unicoin.product.service.ExportOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExportOrderServiceImpl implements ExportOrderService {

    @Autowired
    ExportOrderRepository exportOrderRepository;

    @Autowired
    ExportOrderDetaiRepository exportOrderDetaiRepository;

    @Autowired
    RabbitMQMessageProducer producer;


    @Override
    public List<ExportOrder> viewsAllExportOrder(String userPhoneNumber) {
        log.info("start views exportOrder");
        List<ExportOrder> listData = exportOrderRepository.findAllByUserPhoneNumber(userPhoneNumber);
        if (listData.size() < 0) {
            throw new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        return listData;
    }

    @Override
    public ExportOrderDTO addExportOrder(Long orderId) {
        log.info("start add exportOrders");
        String userPhoneNumber = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        ExportOrder exportOrder = new ExportOrder();
        if (!CommonsUtils.ANONYMOUS_USER.equals(userPhoneNumber)) {
            List<ExportOrder> exportOrders = exportOrderRepository.findAllByUserPhoneNumber(userPhoneNumber);
            if (exportOrders.size() > 0) {
                exportOrder = exportOrders.get(0);
            }
            exportOrder.setUserPhoneNumber(userPhoneNumber);
        } else {
            if (orderId != null) {
                Optional<ExportOrder> optionalExportOrder = exportOrderRepository.findById(orderId);
                if (optionalExportOrder.isPresent()){
                    exportOrder = optionalExportOrder.get();
                }
            }
        }
        exportOrder.setStatus(1);
        exportOrder.setOrderType(1);
        ExportOrder entity = exportOrderRepository.save(exportOrder);
        ExportOrderDTO dto = new ExportOrderDTO();
        BeanUtils.copyProperties(entity, dto);
        log.info("end add exportOrders");
        return dto;
    }

    @Override
    public void addExportOrderDetail(AddExportOrderDetail addExportOrderDetail) {
        log.info("start add exportOrderDetail");
        ExportOrderDetail exportOrderDetail = new ExportOrderDetail();
        Optional<ExportOrder> optionalExportOrder = exportOrderRepository.findById(addExportOrderDetail.getExportOrderId());
        if (optionalExportOrder.isEmpty()) {
            throw new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        List<ExportOrderDetail> orderDetails = exportOrderDetaiRepository.findAllByVariantIdAndAndExportOrderId(addExportOrderDetail.getVariantId(), optionalExportOrder.get());
        if (orderDetails.size() > 0){
                exportOrderDetail = orderDetails.get(0);
            if (addExportOrderDetail.getQuantity() == 0){
                exportOrderDetaiRepository.delete(exportOrderDetail);
            }else {
                exportOrderDetail.setQuantity(addExportOrderDetail.getQuantity());
                exportOrderDetaiRepository.save(exportOrderDetail);
            }
        }else {
            exportOrderDetail.setVariantId(addExportOrderDetail.getVariantId());
            exportOrderDetail.setVariantName(addExportOrderDetail.getVariantName());
            exportOrderDetail.setQuantity(addExportOrderDetail.getQuantity());
            exportOrderDetail.setPrice(addExportOrderDetail.getPrice());
            exportOrderDetail.setExportOrderId(optionalExportOrder.get());
            exportOrderDetaiRepository.save(exportOrderDetail);
        }

        log.info("end add exportOrderDetail");
    }


    @Override
    public void updateExportOrder(Long exportOrderId, Integer status) {
        log.info("start update exportOrders");
        Optional<ExportOrder> check = exportOrderRepository.findById(exportOrderId);
        if (check.isPresent()) {
            ExportOrder exportOrder = check.get();
            exportOrder.setStatus(status);
            exportOrderRepository.save(exportOrder);
        } else {
            throw new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        log.info("end update exportOrders");
    }

    @Override
    public void deleteExportOrderDetail(Long id) {
        log.info("start delete exportOrder");
        Optional<ExportOrderDetail> check = exportOrderDetaiRepository.findById(id);
        if (check.isPresent()) {
            exportOrderDetaiRepository.deleteById(id);
        } else {
            throw new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        log.info("end delete exportOrder");
    }

    @Override
    public void checkoutOrder(CheckoutExportOrders orders) {
        log.info("Start checkout Order: {}", orders);
        Optional<ExportOrder> optionalExportOrder = exportOrderRepository.findById(orders.getId());
        if (optionalExportOrder.isEmpty())
            throw new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        String userPhoneNumber = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        ExportOrder exportOrder = optionalExportOrder.get();
        if (CommonsUtils.ANONYMOUS_USER.equals(userPhoneNumber)){
         exportOrder.setUserPhoneNumber(orders.getPhoneRecipient());
        }
        exportOrder.setAddress(orders.getAddress());
        exportOrder.setNameRecipient(orders.getNameRecipient());
        exportOrder.setPhoneRecipient(orders.getPhoneRecipient());
        exportOrder.setRegistStamp(new Timestamp(new Date().getTime()));
        exportOrder.setOrderType(1);
        ExportOrder entity = exportOrderRepository.save(exportOrder);
        List<ExportOrderDetail> orderDetails = exportOrderDetaiRepository.findAllByExportOrderId(entity);
        if (orderDetails.size() == 0)
            throw new AppException(ExceptionCode.EXPORT_ORDER_IS_NOT_CONTAINS_PRODUCT);
        QueueExportOrder queueExportOrder = new QueueExportOrder();
        BeanUtils.copyProperties(entity, queueExportOrder);
        queueExportOrder.setQueueExportOrderDetails(orderDetails.stream().map(item ->
                        QueueExportOrderDetail.builder()
                                .price(item.getPrice())
                                .priceDiscount(item.getPriceDiscount())
                                .quantity(item.getQuantity())
                                .variantId(item.getVariantId())
                                .variantName(item.getVariantName())
                                .build())
                .collect(Collectors.toList()));
        producer.publish(queueExportOrder, RabbitKey.DIRECT_EXCHANGE, RabbitKey.EXPORT_ORDER_ROUTING_KEYS);
        List<ExportOrderDetail> orderDetailList = exportOrderDetaiRepository.findAllByExportOrderId(entity);
        exportOrderDetaiRepository.deleteAll(orderDetailList);
        exportOrderRepository.delete(entity);
        log.info("End checkout Order: {}", orders);
    }

    @Override
    public List<ExportOrderDetailDTO> viewExportOrderByOrderId(Long orderId) {
        Optional<ExportOrder> optionalExportOrder = exportOrderRepository.findById(orderId);
        if (optionalExportOrder.isEmpty())
            throw new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        List<ExportOrderDetail> orderDetails = exportOrderDetaiRepository.findAllByExportOrderId(optionalExportOrder.get());
        List<ExportOrderDetailDTO> detailDTOS = orderDetails.stream().map(item ->
                ExportOrderDetailDTO.builder()
                        .orderId(item.getExportOrderId().getId())
                        .id(item.getId())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .priceDiscount(item.getPriceDiscount())
                        .variantId(item.getVariantId())
                        .variantName(item.getVariantName())
                        .build()).collect(Collectors.toList());
        return detailDTOS;
    }

}
