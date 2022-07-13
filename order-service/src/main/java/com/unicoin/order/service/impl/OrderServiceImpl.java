package com.unicoin.order.service.impl;

import com.unicoin.clients.form.orderform.OrderRequest;
import com.unicoin.order.DTO.ImportOrderDTO;
import com.unicoin.order.DTO.ImportOrderDetailDTO;
import com.unicoin.order.DTO.ImportOrdersDTO;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        importOrders.setUserPhoneNumber("");
        importOrdersRepository.save(importOrders);
        ImportOrdersDTO dto = ImportOrdersDTO.builder().id(importOrders.getId())
                .status(importOrders.getStatus())
                .registStamp(importOrders.getRegistStamp())
                .userPhoneNumber(importOrders.getUserPhoneNumber()).build();
        log.info("end adđ orders and return object");
        return dto;
    }

    @Override
    public void AddImportOrderDetail(List<AddImportOrderDetail> addImportOrderDetail, Long importOrdersId) {
        log.info("start add importOrderDetail");
        Optional<ImportOrders> dataOrders = importOrdersRepository.findById(importOrdersId);
        ImportOrders importOrders = dataOrders.get();
        if(dataOrders.isPresent()){
            for (AddImportOrderDetail i : addImportOrderDetail){
                ImportOrderDetail importOrderDetail = ImportOrderDetail.builder()
                        .variantId(i.getVarianId())
                        .quantity(i.getQuantity())
                        .price(i.getPrice())
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

    @Override
    public List<ImportOrderDTO> getImportOrderByStatus(Integer status){
        List<ImportOrders> list=importOrdersRepository.searchImportOrdersByStatus(status);
        List<ImportOrderDTO> listDTO=list.stream().map(item -> ImportOrderDTO.builder()
                .id(item.getId())
                .userPhoneNumber(item.getUserPhoneNumber())
                .registStamp(item.getRegistStamp())
                .status(item.getStatus())
                .build()).collect(Collectors.toList());
        return listDTO;
    }

    @Override
    public List<ImportOrderDetailDTO> getImportOrderDetailByImportOrdersId(Long id){
        Optional<ImportOrders> optional=importOrdersRepository.findById(id);
        if(optional.isEmpty()) throw new AppException(ExceptionCode.IMPORTORDERSID_NOT_EXIST);
        List<ImportOrderDetail> importOrderDetails= importOrderDetailRepository.findAllByImportOrdersId(optional.get());
        List<ImportOrderDetailDTO> listDTO=importOrderDetails.stream().map(item -> ImportOrderDetailDTO.builder()
                .id(item.getId())
                .variantId(item.getVariantId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .importOrdersId(item.getImportOrdersId().getId())
                .build()).collect(Collectors.toList());
        return listDTO;
    }

}
