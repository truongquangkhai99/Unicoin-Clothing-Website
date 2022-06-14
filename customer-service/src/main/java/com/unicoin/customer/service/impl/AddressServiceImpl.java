package com.unicoin.customer.service.impl;

import com.unicoin.customer.common.RestResponsePage;
import com.unicoin.customer.dto.AddressDTO;
import com.unicoin.customer.entity.User;
import com.unicoin.customer.ex.AppException;
import com.unicoin.customer.ex.ExceptionCode;
import com.unicoin.customer.form.AddAddressForm;
import com.unicoin.customer.repository.AddressRepository;
import com.unicoin.customer.repository.UserRepository;
import com.unicoin.customer.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.unicoin.customer.entity.Address;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public RestResponsePage<AddressDTO> viewsAddress(Integer page, Integer size, Long userId) {
        log.info("start views address by userId");
        Pageable pageable = PageRequest.of((page -1), size );
        Page<Address> pageDto = addressRepository.findAllByUser(userId , pageable);
        if(pageDto.isEmpty()) throw new AppException(ExceptionCode.USER_ID_NOT_EXIST);
        List<AddressDTO> listDto = pageDto.toList().stream().map(item ->AddressDTO.builder()
                .addressId(item.getAddressId())
                .line(item.getLine())
                .status(item.getStatus())
                .registStamp(item.getRegistStamp())
                .build()).collect(Collectors.toList());
        log.info("end views");
        return new  RestResponsePage<>(listDto , page , size , pageDto.getTotalElements() , pageDto.getTotalPages());
    }

    @Override
    public void addAddress(AddAddressForm addAddressForm) {
    log.info("start add Address");
        Address data = new Address();
        Optional<User> checkUserId = userRepository.findById(addAddressForm.getUserId());
        if(checkUserId.isEmpty()){
            throw new AppException(ExceptionCode.USER_ID_NOT_EXIST);
        }
        User user = userRepository.findById(addAddressForm.getUserId()).get();
        data.setLine(addAddressForm.getLine());
        data.setUserId(user);
        data.setStatus(true);
        data.setRegistStamp(new Timestamp(new Date().getTime()));
        addressRepository.save(data);
        log.info("end add address");
    }

    @Override
    public void updateAddress(Long addressId, AddAddressForm addAddressForm) {
    log.info("start update Address");
    Optional<Address> checkId = addressRepository.findById(addressId);
    if(checkId.isPresent()){
        Address data = new Address();
        User user = userRepository.findById(addAddressForm.getUserId()).get();
        data.setLine(addAddressForm.getLine());
        data.setUserId(user);
        data.setRegistStamp(checkId.get().getRegistStamp());
        data.setStatus(true);
        addressRepository.save(data);
        log.info("end update");
    } else {
        throw new AppException(ExceptionCode.ADDRESS_ID_NOT_EXIST);
    }

    }

    @Override
    public void deleteAddress(Long addressId) {
            log.info("start delete ");
            Optional<Address> checkId = addressRepository.findById(addressId);
            if(checkId.isPresent()){
                checkId.get().setStatus(false);
                addressRepository.save(checkId.get());
            } else {throw  new AppException(ExceptionCode.ADDRESS_ID_NOT_EXIST);}
            log.info("end delete");
    }
}
