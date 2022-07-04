package com.unicoin.product.service.impl;

import com.unicoin.product.entity.ExportOrder;
import com.unicoin.product.entity.ExportOrderDetail;
import com.unicoin.product.ex.AppException;
import com.unicoin.product.ex.ExceptionCode;
import com.unicoin.product.form.AddExportOrders;
import com.unicoin.product.form.AddExportOrderDetail;
import com.unicoin.product.repository.ExportOrderDetaiRepository;
import com.unicoin.product.repository.ExportOrderRepository;
import com.unicoin.product.service.ExportOrderService;
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
            throw new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        return listData;
    }

    @Override
    public ExportOrder addExportOrder(AddExportOrders addExportOrders) {
        log.info("start add exportOrders");
        ExportOrder exportOrder=new ExportOrder();
        BeanUtils.copyProperties(addExportOrders,exportOrder);
        exportOrder.setRegistStamp(new Timestamp(new Date().getTime()));
        exportOrder.setStatus(1);
        exportOrderRepository.save(exportOrder);
        log.info("end add exportOrders");
        return exportOrder;
    }

    @Override
    public void addExportOrderDetail(AddExportOrderDetail addExportOrderDetail){
        log.info("start add exportOrderDetail");
        ExportOrderDetail data= new ExportOrderDetail();
        Optional<ExportOrder> check= exportOrderRepository.findById(addExportOrderDetail.getGoodIssueId());
        if(check.isEmpty()){
            throw new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        ExportOrder exportOrder=exportOrderRepository.findById(addExportOrderDetail.getGoodIssueId()).get();
        data.setVariantId(addExportOrderDetail.getVariantId());
        data.setQuantity(addExportOrderDetail.getQuantity());
        data.setPrice(addExportOrderDetail.getPrice());
        data.setGoodIssueId(exportOrder);
        exportOrderDetaiRepository.save(data);
        log.info("end add exportOrderDetail");
    }

    @Override
    public List<ExportOrderDetail> viewsAllExportOrderDetail(Long goodIssueId) {
        log.info("start views exportOrderDetail");
        List<ExportOrderDetail> data= exportOrderDetaiRepository.findAllByGoodIssueId(goodIssueId);
        if(data.size()<0){
            throw  new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        return data;
    }

    @Override
    public void updateExportOrder(Long goodIssueId, Integer status) {
        log.info("start update exportOrders");
        Optional<ExportOrder> check= exportOrderRepository.findById(goodIssueId);
        if(check.isPresent()){
            ExportOrder exportOrder=check.get();
            exportOrder.setStatus(status);
            exportOrderRepository.save(exportOrder);
        }else{
            throw  new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        log.info("end update exportOrders");
    }

    @Override
    public  void deleteExportOrderDetail(Long id){
        log.info("start delete exportOrder");
        Optional<ExportOrderDetail> check=exportOrderDetaiRepository.findById(id);
        if(check.isPresent()){
            exportOrderDetaiRepository.deleteById(id);
        }else{
            throw  new AppException(ExceptionCode.EXPORTORDERS_NOT_EXIST);
        }
        log.info("end delete exportOrder");
    }
}
