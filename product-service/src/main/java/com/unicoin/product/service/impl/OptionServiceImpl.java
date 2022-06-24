package com.unicoin.product.service.impl;

import com.unicoin.product.common.RestResponsePage;
import com.unicoin.product.dto.OptionListDTO;
import com.unicoin.product.entity.*;
import com.unicoin.product.ex.AppException;
import com.unicoin.product.ex.ExceptionCode;
import com.unicoin.product.form.AddOptionListForm;
import com.unicoin.product.repository.*;
import com.unicoin.product.service.OptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OptionServiceImpl implements OptionService {


    @Autowired
    OptionValueRepository optionValueRepository;

    @Autowired
    OptionListRepository optionListRepository;

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    VariantRepository variantRepository;

    @Autowired
    VariantValueRepository variantValueRepository;


//    status = 1 -> active
//    status = 0 -> in-active
//    status = 2 -> wait-active

    @Override
    public RestResponsePage<OptionListDTO> addOptionList(AddOptionListForm form) {
        log.info("Start addOption {}", form);
        if (optionListRepository.existsOptionListByOptionName(form.getOptionName()))
            throw new AppException(ExceptionCode.OPTIONLIST_NAME_IS_EXIST);

        if (optionListRepository.existsOptionListByOptionCode(form.getOptionCode()))
            throw new AppException(ExceptionCode.OPTIONLIST_CODE_IS_EXIST);

        OptionList option = new OptionList();
        BeanUtils.copyProperties(form, option);
        option.setOptionCode(form.getOptionCode().toUpperCase());
        OptionList entity = optionListRepository.save(option);
        OptionListDTO dto = new OptionListDTO();
        BeanUtils.copyProperties(entity, dto);
        List<OptionListDTO> dtoList = new ArrayList<>();
        dtoList.add(dto);
        log.info("End addOption");
        return new RestResponsePage<>(dtoList);
    }

    @Override
    public RestResponsePage<OptionListDTO> viewOptionList() {
        log.info("Start viewOptions");
        List<OptionList> optionList = optionListRepository.findAll();
        List<OptionListDTO> dtoList = optionList.stream().map(
                item -> OptionListDTO.builder()
                        .id(item.getId())
                        .optionName(item.getOptionName())
                        .optionCode(item.getOptionCode())
                        .build()).collect(Collectors.toList());
        log.info("End viewOptions");
        return new RestResponsePage<>(dtoList, 1, optionList.size(), optionList.size(), 1);
    }

    @Override
    public void deleteOptionValue(Long optionValueId) {
        Optional<OptionValue> optionValueOptional = optionValueRepository.findById(optionValueId);
        if (optionValueOptional.isEmpty())
            throw new AppException(ExceptionCode.OPTIONVALUE_IS_NOT_EXIST);

        List<VariantValue> variantValueList = variantValueRepository.findAllByOptionValue(optionValueOptional.get());
        List<OptionValue> optionValueList = optionValueRepository.findAllByOption(optionValueOptional.get().getOption());
        List<Variant> variantList = new ArrayList<>();
        for (VariantValue variantValue :
                variantValueList) {
            variantList.add(variantValue.getVariant());

        }
        List<Option> optionList = optionRepository.findAllByProduct(optionValueOptional.get().getOption().getProduct());
        for (Variant variant : variantList) {
            List<VariantValue> variantValues = variantValueRepository.findAllByVariant(variant);
            for (VariantValue variantValue :
                    variantValues) {
                variantValueRepository.delete(variantValue);
            }
            if (optionValueList.size() == 1 && optionList.size() > 1) {
                String[] skuIds = variant.getSkuId().split("-");
                String skuId = "";
                for (String str :
                        skuIds) {
                    if (!str.contains(optionValueList.get(0).getOption().getOptionCode())) {
                        skuId = skuId + str;
                    }
                }
                variant.setSkuId(skuId);
                variantRepository.save(variant);
            }else {
                variantRepository.delete(variant);
            }
        }
        optionValueRepository.delete(optionValueOptional.get());
    }

}
