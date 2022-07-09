package com.unicoin.product.service.impl;

import com.unicoin.product.dto.ImportOrdersDTO;
import com.unicoin.product.entity.ImportOrderDetail;
import com.unicoin.product.entity.ImportOrders;
import com.unicoin.product.ex.AppException;
import com.unicoin.product.ex.ExceptionCode;
import com.unicoin.product.form.AddImportOrderDetail;
import com.unicoin.product.repository.ImportOrderDetailRepository;
import com.unicoin.product.repository.ImportOrdersRepository;
import com.unicoin.product.service.ImportOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ImportOrdersServiceImpl implements ImportOrdersService {
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
    public void AddImportOrderDetail(List<AddImportOrderDetail> addImportOrderDetail, Long importOrdersId) {
        log.info("start add importOrderDetail");
        Optional<ImportOrders> dataOrders = importOrdersRepository.findById(importOrdersId);
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
            throw  new AppException(ExceptionCode.IMPORTORDERSDETAILID_NOT_EXIST);
        }
    }

    @Override
    public void updateOrderDetail(Long orderId, Integer status) {
        log.info("start update set status orders");
        Optional<ImportOrders> dataCheck = importOrdersRepository.findById(orderId);
        if(dataCheck.isPresent()){
            ImportOrders importOrders = dataCheck.get();
            importOrders.setStatus(status);
            importOrdersRepository.save(importOrders);
        }else {
            throw  new AppException(ExceptionCode.IMPORTORDERSDETAILID_NOT_EXIST);
        }
        log.info("end update status");
    }

    @Override
    public void deleteOrderDetail(Long id) {
        log.info("start delete orderDetail");
        Optional<ImportOrderDetail> dataCheck=importOrderDetailRepository.findById(id);
        if(dataCheck.isPresent()){
            importOrderDetailRepository.deleteById(id);
        }else {
            throw  new AppException(ExceptionCode.IMPORTORDERSDETAILID_NOT_EXIST);
        }
    }
}
