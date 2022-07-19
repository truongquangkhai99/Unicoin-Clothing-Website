package com.unicoin.product.service.impl;

import com.unicoin.amqp.RabbitMQMessageProducer;
import com.unicoin.clients.commons.RabbitKey;
import com.unicoin.clients.rabbitmqModel.QueueExportOrder;
import com.unicoin.clients.rabbitmqModel.QueueExportOrderDetail;
import com.unicoin.product.common.CommonsUtils;
import com.unicoin.product.entity.ExportOrder;
import com.unicoin.product.entity.ExportOrderDetail;
import com.unicoin.product.ex.AppException;
import com.unicoin.product.ex.ExceptionCode;
import com.unicoin.product.form.AddExportOrderDetail;
import com.unicoin.product.form.CheckoutExportOrders;
import com.unicoin.product.repository.ExportOrderDetaiRepository;
import com.unicoin.product.repository.ExportOrderRepository;
import com.unicoin.product.service.ExportOrderCounterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExportOrderCounterServiceimpl implements ExportOrderCounterService {
    @Autowired
    ExportOrderRepository exportOrderRepository;

    @Autowired
    ExportOrderDetaiRepository exportOrderDetaiRepository;

    @Autowired
    RabbitMQMessageProducer producer;

    @Override
    public ExportOrder createExportOrder(CheckoutExportOrders addExportOrders){
        log.info("start create export order counter");
        ExportOrder exportOrder=new ExportOrder();
        String userPhoneNumber = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (CommonsUtils.ANONYMOUS_USER.equals(userPhoneNumber)){
            exportOrder.setUserPhoneNumber(userPhoneNumber);
        }
        BeanUtils.copyProperties(addExportOrders,exportOrder);
        exportOrder.setRegistStamp(new Timestamp(new Date().getTime()));
        exportOrder.setStatus(2);
        exportOrder.setOrderType(0);
        exportOrderRepository.save(exportOrder);
        log.info("end create export order counter");
        return exportOrder;
    }

    @Override
    public void deleteExportOrder(Long orderId){
        log.info("start delete export order counter");
        Optional<ExportOrder> exportOrder = exportOrderRepository.findById(orderId);
        if(exportOrder.isEmpty()){
            throw new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        exportOrderRepository.deleteById(orderId);
        log.info("end delete export order counter");
    }

    @Override
    public void addProductToOrderDetailCounter(AddExportOrderDetail addExportOrderDetail){
        log.info("start add product to export order detail");
        ExportOrderDetail data= new ExportOrderDetail();
        Optional<ExportOrder> exportOrder = exportOrderRepository.findById(addExportOrderDetail.getExportOrderId());
        if(exportOrder.isEmpty()){
            throw new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        data.setVariantId(addExportOrderDetail.getVariantId());
        data.setQuantity(addExportOrderDetail.getQuantity());
        data.setPrice(addExportOrderDetail.getPrice());
        data.setPriceDiscount(addExportOrderDetail.getPriceDiscount());
        data.setExportOrderId(exportOrder.get());
        exportOrderDetaiRepository.save(data);
        log.info("end add product to export order detail");
    }

    @Override
    public void deleteOrderDetailCounter(Long id){
        log.info("start delete order detail counter");
        Optional<ExportOrderDetail> orderDetail = exportOrderDetaiRepository.findById(id);
        if(orderDetail.isPresent()){
            exportOrderDetaiRepository.deleteById(id);
            Integer sizeOrderDetail = exportOrderDetaiRepository.findAll().size();
            if(sizeOrderDetail == 0){
                exportOrderRepository.deleteById(orderDetail.get().getExportOrderId().getId());
            }
        }else{
            throw  new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        log.info("end delete order detail counter");
    }

    @Override
    public void updateOrderDetailCounter(Long id, Integer quantity){
        log.info("start update quantity product");
        Optional<ExportOrderDetail> orderDetail = exportOrderDetaiRepository.findById(id);
        if(orderDetail.isPresent()){
            if(quantity <= 0){
                exportOrderDetaiRepository.deleteById(id);
            }else{
                orderDetail.get().setQuantity(quantity);
                exportOrderDetaiRepository.save(orderDetail.get());
            }
        }else{
            throw  new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        log.info("end update quantity product");
    }

    @Override
    public List<Long> listIdExportOrderCounter(Integer status, Integer type){
        List<ExportOrder> exportOrders = exportOrderRepository.findByStatusAndOrderType(status, type);
        if(exportOrders == null || exportOrders.isEmpty() ) return Collections.emptyList();
        List<Long> ids = exportOrders.stream().
                map(record -> record.getId()).collect(Collectors.toList());
        return ids;
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
        exportOrder.setOrderType(0);
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
        log.info("End checkout Order: {}", orders);
    }

}
