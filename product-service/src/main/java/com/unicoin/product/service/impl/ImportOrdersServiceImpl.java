package com.unicoin.product.service.impl;

import com.unicoin.amqp.RabbitMQMessageProducer;
import com.unicoin.clients.commons.RabbitKey;
import com.unicoin.clients.rabbitmqModel.QueueImportOrder;
import com.unicoin.clients.rabbitmqModel.QueueImportOrderDetail;
import com.unicoin.product.dto.ImportOrdersDTO;
import com.unicoin.product.entity.ImportOrderDetail;
import com.unicoin.product.entity.ImportOrders;
import com.unicoin.product.entity.Variant;
import com.unicoin.product.ex.AppException;
import com.unicoin.product.ex.ExceptionCode;
import com.unicoin.product.form.AddImportOrderDetail;
import com.unicoin.product.repository.ImportOrderDetailRepository;
import com.unicoin.product.repository.ImportOrdersRepository;
import com.unicoin.product.repository.VariantRepository;
import com.unicoin.product.service.ImportOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
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

    @Autowired
    VariantRepository variantRepository;

    @Autowired
    RabbitMQMessageProducer producer;


    @Override
    public ImportOrdersDTO viewsImportOrders() {
        log.info("start add Orders");
        String userPhoneNumber = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        ImportOrders importOrders = new ImportOrders();
        importOrders.setStatus(1);
        importOrders.setRegistStamp(new Timestamp(new Date().getTime()));
        importOrders.setUserPhoneNumber(userPhoneNumber);
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
        if (dataOrders.isPresent()) {
            for (AddImportOrderDetail item : addImportOrderDetail) {
                Optional<Variant> dataVariant = variantRepository.findById(addImportOrderDetail.get(0).getVarianId());
                Variant variant = dataVariant.get();
                List<ImportOrderDetail> checkVariantId = importOrderDetailRepository.findAllByVariantIdAndAndImportOrdersId(variant, importOrders);
                if (item.getQuantity() == 0) {
                    importOrderDetailRepository.deleteById(checkVariantId.get(0).getId());
                } else if (checkVariantId.size() > 0) {
                    Optional<ImportOrderDetail> dataUpdate = importOrderDetailRepository.findById(checkVariantId.get(0).getId());
                    ImportOrderDetail importOrderDetail1 = new ImportOrderDetail();
                    importOrderDetail1 = dataUpdate.get();
                    importOrderDetail1.setQuantity(item.getQuantity());
                    importOrderDetailRepository.save(importOrderDetail1);
                } else {
                    ImportOrderDetail importOrderDetail = ImportOrderDetail.builder()
                            .variantId(variant)
                            .quantity(item.getQuantity())
                            .price(item.getPrice())
                            .importOrdersId(importOrders).build();
                    importOrderDetailRepository.save(importOrderDetail);
                }
            }
            log.info("end add");
        } else {
            throw new AppException(ExceptionCode.IMPORTORDERSDETAILID_NOT_EXIST);
        }
    }
    @Override
    public Long sumPriceImportOrder(Long id){
        Long sumPrice =0L;
        Optional<ImportOrders> data= importOrdersRepository.findById(id);
        ImportOrders importOrders = data.get();
        if(data.isEmpty()){
            throw new AppException(ExceptionCode.IMPORTORDER_IS_NOT_EXIT);
        }else {
            List<ImportOrderDetail> list = importOrderDetailRepository.findAllByImportOrdersId(importOrders);
            if(importOrders.getStatus() == 1){
                for(ImportOrderDetail item : list){
                    sumPrice = sumPrice +item.getPrice()*item.getQuantity();
                }
                return sumPrice;
            }else {
                throw new AppException(ExceptionCode.IMPORTORDER_STATUS_IS_NOT_1);
            }
        }
    }

    @Override
    public void checkoutImportOrder(Long importOrderId) {
        log.info("start checkoutImportOrder");
        Optional<ImportOrders> dataOrder = importOrdersRepository.findById(importOrderId);
        ImportOrders importOrders = dataOrder.get();
        List<ImportOrderDetail> dataOrderDetail = importOrderDetailRepository.findAllByImportOrdersId(importOrders);
        if (importOrders.getStatus() == 0l) {
            for (ImportOrderDetail item : dataOrderDetail) {
                List<QueueImportOrderDetail> dataQueueOrder = new ArrayList<>();
                Variant variant = item.getVariantId();
                QueueImportOrderDetail queueImportOrderDetail = QueueImportOrderDetail.builder()
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .variantId(variant.getId()).build();
                dataQueueOrder.add(queueImportOrderDetail);
                QueueImportOrder queueImportOrder = new QueueImportOrder();
                queueImportOrder.setStatus(importOrders.getStatus());
                queueImportOrder.setRegistStamp(importOrders.getRegistStamp());
                queueImportOrder.setUserPhoneNumber(importOrders.getUserPhoneNumber());
                queueImportOrder.setQueueImportOrderDetails(dataQueueOrder);
                producer.publish(queueImportOrder, RabbitKey.DIRECT_EXCHANGE, RabbitKey.IMPORT_ORDER_ROUTING_KEYS);
                log.info("end checkout");
            }
        }else if(importOrders.getStatus() == 1) {
            throw  new AppException(ExceptionCode.IMPORTORDERDETAIL_STATUS_APPROVED);
        }else {
            throw  new AppException(ExceptionCode.IMPORTORDERDETAIL_STATUS_CANCELLED);
        }
    }
}
