package com.unicoin.customer.controllerTest.ServiceTest;


import com.unicoin.customer.common.RestResponsePage;
import com.unicoin.customer.dto.UserDTO;
import com.unicoin.customer.form.AddCustomerForm;
import com.unicoin.customer.repository.UserRepository;
import com.unicoin.customer.service.impl.UserServiceImpl;
import emulator.BuildObjectUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl mockService;

    @Mock
    UserRepository mockRepository;

    private BuildObjectUtils buildObjectUtils = new BuildObjectUtils();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ViewsCustomerTest_ShouldReturnSuccess() throws  Exception{
        RestResponsePage<UserDTO> expects = buildObjectUtils.buidViewsCustomer();
       when(mockRepository.searchAllCustomer(any(),any(),any(),any())).thenReturn(buildObjectUtils.buildPageUser());
       RestResponsePage<UserDTO> actual = mockService.viewCustomer(1 , 10 , "0989875657"
               ,"quangdz", "quangvmph12936@fpt.edu.vn");
       assertEquals(expects.getContent().get(0).getPhoneNumber(), actual.getContent().get(0).getPhoneNumber());
    }

    @Test
    public void addCustomerTest_ShouldReturnSuccess() throws  Exception{
        AddCustomerForm form = buildObjectUtils.addCustomerFormData();
        when(mockRepository.findByPhoneNumber(any())).thenReturn(null);
        when(mockRepository.findByEmail(any())).thenReturn(null);
        when(mockRepository.save(any())).thenReturn(null);
        mockService.addCustomer(form);
    }
}
