package com.unicoin.customer.controllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicoin.customer.controller.admin.UserController;
import com.unicoin.customer.entity.Role;
import com.unicoin.customer.repository.RoleRepository;
import com.unicoin.customer.service.UserService;
import emulator.BuildObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserControllerTest {
    @InjectMocks
    UserController mockUserController;

    @Mock
    UserService mockUserService;

    @Autowired
    RoleRepository roleRepository;
    protected MockMvc mockMvc;

    private BuildObjectUtils buildObjectUtils = new BuildObjectUtils();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(mockUserController).build();
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
    public void viewCustomerTest_ShouldReturnSuccess() throws  Exception{
        String url ="/admin/customer";
        mockUserService = mock(UserService.class);
        when(mockUserService.viewCustomer(anyInt(),anyInt(),anyString(),anyString(),anyString())).thenReturn(buildObjectUtils.buidViewsCustomer());
        MultiValueMap<String , String> param = new LinkedMultiValueMap<>();
        param.add("phoneNumber" , "0989875657");
        param.add("fullName" , "quangdz");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url).params(param).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200 , status);
    }

    @Test
    public void AddCustomerTest_ShouldReturnSuccess() throws  Exception{
        String url ="/admin/customer/add";
        mockUserService = mock(UserService.class);
        if(mockMvc == null){
            throw  new Exception("mvc is having null value");
        }
        doNothing().when(mockUserService).updateCustomer(any(),isNull());
        String inputJson = mapToJson(buildObjectUtils.buildCustomerForm());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        int Status = result.getResponse().getStatus();
        assertEquals(200 , Status);
    }

    @Test
    public void UpdateCustomerTest_ShouldReturnSuccess() throws Exception{
        String url = "/admin/customer/update/{id}";
        mockUserService = mock(UserService.class);
        if(mockMvc ==  null){ throw new Exception("mvc is having null value"); }
        doNothing().when(mockUserService).updateCustomer(any(),isNull());
        String inputJson = mapToJson(buildObjectUtils.buildCustomerForm());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(url , "id",1l).contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(400 , status);
    }

    @Test
    public void DeleteCustomer_ShouldReturnSuccess() throws Exception{
        String url = "/admin/customer/{phoneNumber}";
        mockUserService = mock(UserService.class);
        doNothing().when(mockUserService).uDeleteCustomer(anyString());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(url , "phoneNumber","0989875657").contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200 , status);
    }

    @Test
    public void loginTest_ShouldReturnSuccess() throws Exception {
        mockUserService = mock(UserService.class);
        when(mockUserService.login()).thenReturn(null);
        String url = "/admin/customer/login";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status =  result.getResponse().getStatus();
        assertEquals(200 , status);
    }

    @Test
    public void getRoleTest_ShouldReturnSuccess()  throws Exception {
        mockUserService = mock(UserService.class);
    when(mockUserService.getRoles()).thenReturn(buildObjectUtils.buildGetPageRole());
    String url = "/admin/customer/getRoles";
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andReturn();
    int status = result.getResponse().getStatus();
    assertEquals(200 , status);
    }

    @Test
    public void AddRoleFromTesst_ShouldReturnSuccess() throws  Exception {
        mockUserService = mock(UserService.class);
        doNothing().when(mockUserService).addRole(isNull());
        String url = "/admin/customer/addRole";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200 , status);
    }
}
