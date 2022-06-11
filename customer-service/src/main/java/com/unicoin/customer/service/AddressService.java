package com.unicoin.customer.service;
import com.unicoin.customer.common.RestResponsePage;
import com.unicoin.customer.dto.AddressDTO;
import com.unicoin.customer.form.AddAddressForm;

public interface AddressService {
    RestResponsePage<AddressDTO> viewsAddress(Integer page, Integer size, Long userId);

    void addAddress(AddAddressForm addAddressForm);
    void updateAddress(Long addressId , AddAddressForm addAddressForm );
    void deleteAddress(Long addressId);
}
