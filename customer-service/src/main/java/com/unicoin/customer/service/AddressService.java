package com.unicoin.customer.service;
import com.unicoin.customer.common.RestResponsePage;
import com.unicoin.customer.entity.Address;
import com.unicoin.customer.form.AddAddressForm;

public interface AddressService {
    RestResponsePage<Address> viewsAddress(Long userId);

    void addAddress(AddAddressForm addAddressForm);
    void updateAddress(Long addressId , AddAddressForm addAddressForm );
    void deleteAddress(Long addressId);
}
