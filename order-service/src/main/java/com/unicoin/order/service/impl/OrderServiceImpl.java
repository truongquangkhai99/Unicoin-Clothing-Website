package com.unicoin.order.service.impl;

import com.unicoin.clients.rabbitmqModel.QueueImportOrder;
import com.unicoin.clients.rabbitmqModel.QueueImportOrderDetail;
import com.unicoin.order.DTO.ImportOrdersDTO;
import com.unicoin.order.DTO.ImportOrderDetailDTO;
import com.unicoin.order.entity.ImportOrderDetail;
import com.unicoin.order.entity.ImportOrders;
import com.unicoin.order.ex.AppException;
import com.unicoin.order.ex.ExceptionCode;
import com.unicoin.order.repository.ImportOrderDetailRepository;
import com.unicoin.order.repository.ImportOrdersRepository;
import com.unicoin.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addImportOrderFromQueues(QueueImportOrder queueImportOrder) {
        log.info("start add data  from queues");
        try {
            ImportOrders importOrders = ImportOrders.builder()
                    .id(queueImportOrder.getId())
                    .userPhoneNumber(queueImportOrder.getUserPhoneNumber())
                    .registStamp(queueImportOrder.getRegistStamp())
                    .status(queueImportOrder.getStatus()).build();
            importOrdersRepository.save(importOrders);
            List<QueueImportOrderDetail> listData = queueImportOrder.getQueueImportOrderDetails();
            for (QueueImportOrderDetail item : listData) {
                ImportOrderDetail importOrderDetail = ImportOrderDetail.builder()
                        .id(item.getId())
                        .variantId(item.getVariantId())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .importOrdersId(importOrders).build();
                log.info("dataDetail" + importOrderDetail);
                importOrderDetailRepository.save(importOrderDetail);
            }
            log.info("end add ImportOrder from queues");
        } catch (Exception e) {
            throw new AppException(ExceptionCode.SAVE_IMPORTORDER_FAILS);
        }
    }

    @Override
    public void updateOrderByStatus(Long orderId, Integer status) {
        log.info("start update set status orders");
        Optional<ImportOrders> dataCheck = importOrdersRepository.findById(orderId);
        if (dataCheck.isPresent()) {
            ImportOrders importOrders = dataCheck.get();
            importOrders.setStatus(status);
            importOrdersRepository.save(importOrders);
        } else {
            throw new AppException(ExceptionCode.IMPORTORDERSID_NOT_EXIST);
        }
        log.info("end update status");
    }

    @Override
    public List<ImportOrdersDTO> getImportOrderByStatus(Integer status) {
        List<ImportOrders> list = importOrdersRepository.searchImportOrdersByStatus(status);
        List<ImportOrdersDTO> listDTO = list.stream().map(item -> ImportOrdersDTO.builder()
                .id(item.getId())
                .userPhoneNumber(item.getUserPhoneNumber())
                .registStamp(item.getRegistStamp())
                .status(item.getStatus())
                .build()).collect(Collectors.toList());
        return listDTO;
    }

    @Override
    public List<ImportOrderDetailDTO> getImportOrderDetailByImportOrdersId(Long id) {
        Optional<ImportOrders> optional = importOrdersRepository.findById(id);
        if (optional.isEmpty()) throw new AppException(ExceptionCode.IMPORTORDERSID_NOT_EXIST);
        List<ImportOrderDetail> importOrderDetails = importOrderDetailRepository.findAllByImportOrdersId(optional.get());
        List<ImportOrderDetailDTO> listDTO = importOrderDetails.stream().map(item -> ImportOrderDetailDTO.builder()
                .id(item.getId())
                .variantId(item.getVariantId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .importOrdersId(item.getImportOrdersId().getId())
                .build()).collect(Collectors.toList());
        return listDTO;
    }

}
