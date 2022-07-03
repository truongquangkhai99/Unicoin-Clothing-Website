package com.unicoin.customer.controllerTest.ServiceTest;

import com.unicoin.customer.repository.AddressRepository;
import com.unicoin.customer.repository.UserRepository;
import com.unicoin.customer.service.AddressService;
import com.unicoin.customer.service.impl.AddressServiceImpl;
import emulator.BuildObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AddressServiceImplTest {

    @InjectMocks
    AddressServiceImpl mockService;

    @Mock
    AddressRepository mockRepository;

    @Mock
    UserRepository userRepository;

    private BuildObjectUtils buildObjectUtils = new BuildObjectUtils();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void viewsAddressTest_ShouldReturnSuccess() throws  Exception{
        mockService = mock(AddressServiceImpl.class);
        when(mockService.viewsAddress(any())).thenReturn(buildObjectUtils.buildPageAddress());
        when(mockRepository.findAllByUserId(any())).thenReturn(buildObjectUtils.buildListAddressEntity());

    }
}
