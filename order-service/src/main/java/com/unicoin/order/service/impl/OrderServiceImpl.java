package com.unicoin.order.service.impl;

import com.mysql.cj.log.Log;
import com.unicoin.clients.form.orderform.OrderRequest;
import com.unicoin.order.config.ImportOrdersDTO;
import com.unicoin.order.entity.ImportOrderDetail;
import com.unicoin.order.entity.ImportOrders;
import com.unicoin.order.entity.Order;
import com.unicoin.order.ex.AppException;
import com.unicoin.order.ex.ExceptionCode;
import com.unicoin.order.form.AddImportOrderDetail;
import com.unicoin.order.repository.ImportOrderDetailRepository;
import com.unicoin.order.repository.ImportOrdersRepository;
import com.unicoin.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    ImportOrdersRepository importOrdersRepository;

    @Autowired
    ImportOrderDetailRepository importOrderDetailRepository;

    @Override
    public ImportOrdersDTO viewsImportOrders() {
        log.info("start add Orders");
        ImportOrders importOrders = new ImportOrders();
        importOrders.setStatus(1);
        importOrders.setRegistStamp(new Timestamp(new Date().getTime()));
        importOrders.setUserId(1l);
        importOrdersRepository.save(importOrders);
        ImportOrdersDTO dto = ImportOrdersDTO.builder().id(importOrders.getId())
                .status(importOrders.getStatus())
                .registStamp(importOrders.getRegistStamp())
                .userId(importOrders.getUserId()).build();
        log.info("end adđ orders and return object");
        return dto;
    }

    @Override
    public void AddImportOrderDetail(List<AddImportOrderDetail> addImportOrderDetail, Long importOrderId) {
        log.info("start add importOrderDetail");
        Optional<ImportOrders> dataOrders = importOrdersRepository.findById(importOrderId);
        ImportOrders importOrders = dataOrders.get();
        if(dataOrders.isPresent()){
            for (AddImportOrderDetail i : addImportOrderDetail){
                ImportOrderDetail importOrderDetail = ImportOrderDetail.builder()
                        .variantId(i.getVarianId())
                        .quantity(i.getQuantity())
                        .cost(i.getCost())
                        .importOrdersId(importOrders).build();
                importOrderDetailRepository.save(importOrderDetail);
            }
            log.info("end add");
        }else {
                throw  new AppException(ExceptionCode.IMPORTORDERSID_NOT_EXIST);
        }
    }

    @Override
    public void saveOrder(OrderRequest request) {
        Order order = Order.builder()
                .createAt(request.getCreateAt())
                .customerFistName(request.getCustomerFistName())
                .build();
        OrderRequest orderRequest = new OrderRequest();
        BeanUtils.copyProperties(order, orderRequest);

    }

    @Override
    public void updateOrderDetail(Long orderId , Integer status) {
        log.info("start update set status orders");
        Optional<ImportOrders> dataCheck = importOrdersRepository.findById(orderId);
        if(dataCheck.isPresent()){
            ImportOrders importOrders = dataCheck.get();
            importOrders.setStatus(status);
            importOrdersRepository.save(importOrders);
        }else {
            throw  new AppException(ExceptionCode.IMPORTORDERSID_NOT_EXIST);
        }
        log.info("end update status");
    }

}
