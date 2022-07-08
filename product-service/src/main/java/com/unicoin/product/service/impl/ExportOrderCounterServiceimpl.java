package com.unicoin.product.service.impl;

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
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class ExportOrderCounterServiceimpl implements ExportOrderCounterService {
    @Autowired
    ExportOrderRepository exportOrderRepository;

    @Autowired
    ExportOrderDetaiRepository exportOrderDetaiRepository;

    @Override
    public ExportOrder createExportOrder(CheckoutExportOrders addExportOrders){
        log.info("start create export order counter");
        ExportOrder exportOrder=new ExportOrder();
        BeanUtils.copyProperties(addExportOrders,exportOrder);
        exportOrder.setRegistStamp(new Timestamp(new Date().getTime()));
        exportOrder.setStatus(4);
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
}
