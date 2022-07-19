package com.unicoin.order.service.impl;

import com.unicoin.clients.rabbitmqModel.QueueExportOrder;
import com.unicoin.clients.rabbitmqModel.QueueExportOrderDetail;
import com.unicoin.order.DTO.ExportOrderDTO;
import com.unicoin.order.DTO.ExportOrderDetailDTO;
import com.unicoin.order.common.RestResponsePage;
import com.unicoin.order.entity.ExportOrder;
import com.unicoin.order.entity.ExportOrderDetail;
import com.unicoin.order.ex.AppException;
import com.unicoin.order.ex.ExceptionCode;
import com.unicoin.order.form.AddExportOrders;
import com.unicoin.order.form.FormExportOrderDetail;
import com.unicoin.order.repository.ExportOrderDetaiRepository;
import com.unicoin.order.repository.ExportOrderRepository;
import com.unicoin.order.service.ExportOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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


    @Override
    public RestResponsePage<ExportOrderDTO> guestViewsAllExportOrderByUserPhoneNumber() {
        log.info("start views exportOrder");
        String userPhoneNumber = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        List<ExportOrder> listData = exportOrderRepository.findAllByUserPhoneNumber(userPhoneNumber);
        if(listData.size() < 0){
            throw  new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        List<ExportOrderDTO> exportOrderDTOList = listData.stream().map(item ->
                ExportOrderDTO.builder()
                        .id(item.getId())
                        .address(item.getAddress())
                        .nameRecipient(item.getNameRecipient())
                        .userPhoneNumber(item.getUserPhoneNumber())
                        .registStamp(item.getRegistStamp())
                        .phoneRecipient(item.getPhoneRecipient())
                        .registStamp(item.getRegistStamp())
                        .orderType(item.getOrderType())
                        .status(item.getStatus())
                        .build()
                ).collect(Collectors.toList());

        return new RestResponsePage<>(exportOrderDTOList);
    }

    @Override
    public RestResponsePage<ExportOrderDetailDTO> guestViewsAllExportOrderDetail(Long orderId) {
        Optional<ExportOrder> optionalExportOrder = exportOrderRepository.findById(orderId);
        if (optionalExportOrder.isEmpty())
            throw new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        List<ExportOrderDetail> orderDetailList = exportOrderDetaiRepository.findAllByExportOrderId(optionalExportOrder.get());

        List<ExportOrderDetailDTO> orderDetailDTOList = orderDetailList.stream().map(item ->
                ExportOrderDetailDTO.builder()
                        .id(item.getId())
                        .exportOrderId(item.getExportOrderId().getId())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .variantName(item.getVariantName())
                        .variantId(item.getVariantId())
                        .build()
                ).collect(Collectors.toList());
        return new RestResponsePage<>(orderDetailDTOList);
    }

    @Override
    public void addExportOrder(QueueExportOrder queueExportOrder) {
        log.info("start add exportOrders: {}", queueExportOrder);
        ExportOrder exportOrder = ExportOrder.builder()
                .userPhoneNumber(queueExportOrder.getUserPhoneNumber())
                .nameRecipient(queueExportOrder.getNameRecipient())
                .phoneRecipient(queueExportOrder.getPhoneRecipient())
                .address(queueExportOrder.getAddress())
                .registStamp(queueExportOrder.getRegistStamp())
                .orderType(queueExportOrder.getOrderType())
                .status(queueExportOrder.getStatus())
                .build();
        ExportOrder entity = exportOrderRepository.save(exportOrder);
        log.info("save exportOder: {}", entity);
        List<QueueExportOrderDetail> listData= queueExportOrder.getQueueExportOrderDetails();
        for (QueueExportOrderDetail i : listData){
            ExportOrderDetail exportOrderDetail=ExportOrderDetail.builder()
                    .variantId(i.getVariantId())
                    .quantity(i.getQuantity())
                    .variantName(i.getVariantName())
                    .price(i.getPrice())
                    .exportOrderId(entity)
                    .build();
            exportOrderDetaiRepository.save(exportOrderDetail);
        }
        log.info("end add exportOders");
    }

    @Override
    public void updateExportOrder(Long exportOrderId, Integer status) {
        log.info("start update export order");
        Optional<ExportOrder> optionalExportOrder= exportOrderRepository.findById(exportOrderId);
        if(optionalExportOrder.isPresent()){
            ExportOrder exportOrder=optionalExportOrder.get();
            exportOrder.setStatus(status);
            exportOrderRepository.save(exportOrder);
        }else{
            throw  new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        log.info("end update exportOders");
    }

    @Override
    public RestResponsePage<ExportOrderDTO> viewsAllExportOrder(Integer status) {
        List<ExportOrder> exportOrderList = exportOrderRepository.searchExportOrderByStatus(status);

        if(exportOrderList.size() < 0){
            throw  new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        List<ExportOrderDTO> exportOrderDTOList = exportOrderList.stream().map(item ->
                ExportOrderDTO.builder()
                        .id(item.getId())
                        .address(item.getAddress())
                        .nameRecipient(item.getNameRecipient())
                        .userPhoneNumber(item.getUserPhoneNumber())
                        .registStamp(item.getRegistStamp())
                        .phoneRecipient(item.getPhoneRecipient())
                        .registStamp(item.getRegistStamp())
                        .status(item.getStatus())
                        .orderType(item.getOrderType())
                        .build()
        ).collect(Collectors.toList());

        return new RestResponsePage<>(exportOrderDTOList);
    }
}
