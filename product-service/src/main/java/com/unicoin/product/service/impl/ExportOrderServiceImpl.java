package com.unicoin.product.service.impl;

import com.unicoin.amqp.RabbitMQMessageProducer;
import com.unicoin.clients.commons.RabbitKey;
import com.unicoin.clients.rabbitmqModel.QueueExportOrder;
import com.unicoin.clients.rabbitmqModel.QueueExportOrderDetail;
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
import org.springframework.stereotype.Service;

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
    public List<ExportOrder> viewsAllExportOrder(Integer userId) {
        log.info("start views exportOrder");
        List<ExportOrder> listData = exportOrderRepository.findAllByUsedId(userId);
        if (listData.size() < 0) {
            throw new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        return listData;
    }

    @Override
    public ExportOrder addExportOrder() {
        log.info("start add exportOrders");
        ExportOrder exportOrder = new ExportOrder();
        List<ExportOrder> exportOrders = exportOrderRepository.findAllByUsedId(1);
        if (exportOrders.size() > 0){
            exportOrder = exportOrders.get(0);
        }
        exportOrder.setStatus(1);
        exportOrder.setUsedId(1);
        exportOrderRepository.save(exportOrder);
        log.info("end add exportOrders");
        return exportOrder;
    }

    @Override
    public void addExportOrderDetail(AddExportOrderDetail addExportOrderDetail) {
        log.info("start add exportOrderDetail");
        ExportOrderDetail data = new ExportOrderDetail();
        Optional<ExportOrder> check = exportOrderRepository.findById(addExportOrderDetail.getExportOrderId());
        if (check.isEmpty()) {
            throw new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        ExportOrder exportOrder = exportOrderRepository.findById(addExportOrderDetail.getExportOrderId()).get();
        data.setVariantId(addExportOrderDetail.getVariantId());
        data.setVariantName(addExportOrderDetail.getVariantName());
        data.setQuantity(addExportOrderDetail.getQuantity());
        data.setPrice(addExportOrderDetail.getPrice());
        data.setExportOrderId(exportOrder);
        exportOrderDetaiRepository.save(data);
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

        ExportOrder exportOrder = optionalExportOrder.get();
        exportOrder.setAddress(orders.getAddress());
        exportOrder.setNameRecipient(orders.getNameRecipient());
        exportOrder.setPhoneRecipient(orders.getPhoneRecipient());
        ExportOrder entity = exportOrderRepository.save(exportOrder);
        List<ExportOrderDetail> orderDetails = exportOrderDetaiRepository.findAllByExportOrderId(entity);
        if (orderDetails.size() == 0)
            throw new AppException(ExceptionCode.EXPORT_ORDER_IS_NOT_CONTAINS_PRODUCT);
        QueueExportOrder queueExportOrder = new QueueExportOrder();
        BeanUtils.copyProperties(entity, queueExportOrder);
        queueExportOrder.setQueueExportOrderDetails(orderDetails.stream().map(item ->
                QueueExportOrderDetail.builder()
                        .id(item.getId())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .variantId(item.getVariantId())
                        .build())
                .collect(Collectors.toList()));
        producer.publish(queueExportOrder, RabbitKey.DIRECT_EXCHANGE, RabbitKey.EXPORT_ORDER_ROUTING_KEYS);
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
                        .variantId(item.getVariantId())
                        .variantName(item.getVariantName())
                        .build()).collect(Collectors.toList());
        return detailDTOS;
    }

    @Override
    public Long sumOderPrice(Long id) {
        log.info("-ExportOrderServiceImpl:sumOderPrice ,Start show sum price order ,exportOrderId=", id);
        Long sumPrice = 0L;
        Optional<ExportOrder> data = exportOrderRepository.findById(id);
        ExportOrder exportOrder = data.get();
        if (data.isEmpty()) {
            throw new AppException(ExceptionCode.EXPORTORDER_IS_NOT_EXIT);
        } else {
            List<ExportOrderDetail> list = exportOrderDetaiRepository.findAllByExportOrderId(exportOrder);
            if (exportOrder.getStatus() == 1) {
                for (ExportOrderDetail item : list) {
                    sumPrice = sumPrice + item.getPrice();
                }
                return sumPrice;
            } else {
                throw new AppException(ExceptionCode.EXPORTORDER_STATUS_IS_NOT_1);
            }
        }
    }
}
