package com.unicoin.order.service.impl;

import com.unicoin.order.DTO.ExportOrderDTO;
import com.unicoin.order.DTO.ExportOrderDetailDTO;
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
    public List<ExportOrder> viewsAllExportOrder(Integer userId) {
        log.info("start views exportOrder");
        List<ExportOrder> listData = exportOrderRepository.findAllByUsedId(userId);
        if(listData.size() < 0){
            throw  new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        return listData;
    }

    @Override
    public void addExportOrderDetail(AddExportOrders addExportOrders) {
        log.info("start add exportOrders");
        ExportOrder exportOrder = ExportOrder.builder()
                .id(addExportOrders.getId())
                .usedId(addExportOrders.getUsedId())
                .nameRecipient(addExportOrders.getNameRecipient())
                .phoneRecipient(addExportOrders.getPhoneRecipient())
                .address(addExportOrders.getAddress())
                .registStamp(addExportOrders.getRegistStamp())
                .status(addExportOrders.getStatus())
                .build();
        exportOrderRepository.save(exportOrder);
        List<FormExportOrderDetail> listData= addExportOrders.getData();
        for (FormExportOrderDetail i : listData){
            ExportOrderDetail exportOrderDetail=ExportOrderDetail.builder()
                    .id(i.getId())
                    .variantId(i.getVariantId())
                    .quantity(i.getQuantity())
                    .price(i.getPrice())
                    .exportOrderId(i.getExportOrderId())
                    .build();
            exportOrderDetaiRepository.save(exportOrderDetail);
        }
        log.info("end add exportOders");
    }

    @Override
    public void updateExportOrder(Long exportOrderId, Integer status) {
        log.info("start update export order");
        Optional<ExportOrder> check= exportOrderRepository.findById(exportOrderId);
        if(check.isPresent()){
            ExportOrder exportOrder=check.get();
            exportOrder.setStatus(status);
            exportOrderRepository.save(exportOrder);
        }else{
            throw  new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        log.info("end update exportOders");
    }



    @Override
    public List<ExportOrderDTO> getExportOrderByStatus(Integer status){
        List<ExportOrder> list=exportOrderRepository.searchExportOrderByStatus(status);
        List<ExportOrderDTO> listDTO=list.stream().map(item->ExportOrderDTO.builder()
                .id(item.getId())
                .usedId(item.getUsedId())
                .nameRecipient(item.getNameRecipient())
                .phoneRecipient(item.getPhoneRecipient())
                .address(item.getAddress())
                .registStamp(item.getRegistStamp())
                .status(item.getStatus())
                .build()).collect(Collectors.toList());
        return listDTO;
    }

    @Override
    public List<ExportOrderDetailDTO> getExportOrderDetailByExportOrderId(Long id) {
        Optional<ExportOrder> optional = exportOrderRepository.findById(id);
        if (optional.isEmpty()) throw  new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        List<ExportOrderDetail> exportOrderDetails = exportOrderDetaiRepository.findAllByExportOrderId(optional.get());
        List<ExportOrderDetailDTO> listDTO=exportOrderDetails.stream().map(item -> ExportOrderDetailDTO.builder()
                .id(item.getId())
                .variantId(item.getVariantId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .exportOrderId(item.getExportOrderId().getId())
                .build()).collect(Collectors.toList());
        return listDTO;
    }

    @Override
    public List<ExportOrder> viewExportOrderByUserId(Long id) {
        return null;
    }

    @Override
    public List<ExportOrder> viewExportOrderByOption(Long id){
        log.info("Start víews");
        List<ExportOrder> data=exportOrderRepository.viewExportOrderByUserId(id);
        log.info("data"+data);
        return data;
    }
}
