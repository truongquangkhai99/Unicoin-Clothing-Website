package com.unicoin.customer.controllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicoin.customer.controller.AddressController;
import com.unicoin.customer.service.AddressService;
import emulator.BuildObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AddressControllerTest {
    @InjectMocks
    AddressController mockAddressController;

    @Mock
    AddressService mockAddressService;

    private MockMvc mockMvc ;

    private BuildObjectUtils buildObjectUtils;

    @BeforeEach
    public void Setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(mockAddressController).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void  GetAddress_ShouldReturnSuccess() throws Exception{
        String url ="/admin/customer/address";
        mockAddressService = mock(AddressService.class);
        when(mockAddressService.viewsAddress(any(),any(),any())).thenReturn(buildObjectUtils.buildPageAddress());
        if(mockMvc == null){ throw  new Exception("mvc null");}
        MultiValueMap<String , String> param = new LinkedMultiValueMap<>();
        param.add("userId" , "1");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON).params(param)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void AddAddressTest_ShouldReturnSuccess() throws Exception{
        String url = "/admin/customer/address/add";
        mockAddressService=mock(AddressService.class);
       doNothing().when(mockAddressService).addAddress(any());
        if(mockMvc == null){ throw  new Exception("mvc null");}
        String inputJson = mapToJson(buildObjectUtils.addAddressForm());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url).accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200 , status);
    }

    @Test
    public void UpdateAddressTest_ShouldReturnSuccess() throws  Exception{
        String url = "/admin/customer/address/update/{addressId}";
        mockAddressService = mock(AddressService.class);
        String inputJson = mapToJson(buildObjectUtils.addAddressForm());
        if(mockMvc == null){throw  new Exception("mvc null");}
        doNothing().when(mockAddressService).updateAddress(any(), isNull());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(url , "1").accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200 , status);
    }

    @Test
    public  void DeleteAddressTest_ShouldReturnSuccess() throws Exception{
        String url = "/admin/customer/address/update/{addressId}";
    }
}
