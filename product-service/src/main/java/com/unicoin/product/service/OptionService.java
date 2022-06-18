package com.unicoin.product.service;

import com.unicoin.product.common.RestResponsePage;
import com.unicoin.product.dto.OptionListDTO;
import com.unicoin.product.form.AddOptionListForm;

public interface OptionService {

    RestResponsePage<OptionListDTO> addOptionList(AddOptionListForm form);

    RestResponsePage<OptionListDTO> viewOptionList();

    void deleteOptionValue(Long optionValueId);

}
