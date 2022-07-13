package emulator;

import com.unicoin.customer.common.RestResponsePage;
import com.unicoin.customer.dto.AddressDTO;
import com.unicoin.customer.dto.UserDTO;
import com.unicoin.customer.entity.Address;
import com.unicoin.customer.entity.Role;
import com.unicoin.customer.entity.User;
import com.unicoin.customer.form.AddAddressForm;
import com.unicoin.customer.form.AddCustomerForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BuildObjectUtils {
    public UserDTO UserDataOutputDto(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1l);
        userDTO.setFullName("quangdz");
        userDTO.setEmail("quangvmph12936@fpt.edu.vn");
        userDTO.setPhoneNumber("0989875657");
        userDTO.setStatus(true);
        Timestamp timestamp = Timestamp.valueOf("2022-06-22 08:51:23");
        userDTO.setRegistStamp(timestamp);
        return userDTO;
    }

    public User UserData(){
        User user = new User();
        user.setId(1l);
        user.setStatus(true);
        user.setEmail("quangvmph12936@fpt.edu.vn");
        user.setFullName("quangdz");
        user.setPassword("123");
        user.setPhoneNumber("0989875657");
        Timestamp timestamp = Timestamp.valueOf("2022-06-22 08:51:23");
        user.setRegistStamp(timestamp);
        return  user;
    }

    public AddCustomerForm addCustomerFormData(){
        AddCustomerForm addCustomerForm = new AddCustomerForm();
        addCustomerForm.setFullName("quangdz");
        addCustomerForm.setEmail("quangvmph12936@fpt.edu.vn");
        addCustomerForm.setPhoneNumber("0989875657");
        addCustomerForm.setPassword("123");
        return addCustomerForm;
    }

    public List<User> buildListUser(){
        List<User> list = new ArrayList<>();
        list.add(UserData());
        return list;
    }

    public RestResponsePage<UserDTO> buidViewsCustomer(){
        return new RestResponsePage<UserDTO>(buildListViewCustomer(),1,1,1,1);
    }

    public List<UserDTO> buildListViewCustomer(){
        List<UserDTO> dtoList = new ArrayList<>();
        dtoList.add(UserDataOutputDto());
        return dtoList;
    }

    public AddCustomerForm buildCustomerForm(){
        AddCustomerForm addCustomerForm = new AddCustomerForm();
        addCustomerForm.setFullName("quangdz35");
        addCustomerForm.setPhoneNumber("0354778709");
        addCustomerForm.setEmail("hoichanrauvn@gmail.com.vn");
        addCustomerForm.setPassword("9999");
        return  addCustomerForm;
    }

    public Role getRoleEntity(){
        Role role = new Role();
        role.setId(1l);
        role.setRoleName("admin");
        role.setStatus(true);
        Timestamp timestamp = Timestamp.valueOf("2022-06-22 14:30:36");
        role.setMemo("adm");
        role.setRegistStamp(timestamp);
        return role;
    }

    public List<Role> buildGetListRole(){
        List<Role> roles = new ArrayList<>();
        roles.add(getRoleEntity());
        return roles;
    }

    public RestResponsePage<Role> buildGetPageRole(){
        return new RestResponsePage<Role>(buildGetListRole());
    }

    public Page<User> buildPageUser(){
        Pageable pageable = PageRequest.of(1 ,1);
        Page<User> page = new PageImpl<>(buildListUser(),pageable,buildListUser().size());
        return  page;
    }
//build data address
    public AddressDTO addressData(){
        AddressDTO address = new AddressDTO();
        address.setAddressId(1l);
        address.setLine("35 le duc tho");
        address.setStatus(true);
        Timestamp timestamp = Timestamp.valueOf("2022-06-26 01:05:09");
        address.setRegistStamp(timestamp);
        return address;
    }

    public AddAddressForm addAddressForm(){
        AddAddressForm addAddressForm = new AddAddressForm();
        addAddressForm.setLine("36 le duc tho");
        addAddressForm.setUserId(1l);
        return addAddressForm;
    }

    public Address addressDataEntity(){
        Address address = new Address();
        address.setId(1l);
        address.setUser(UserData());
        address.setLine("35 le duc tho");
        address.setStatus(true);
        Timestamp timestamp = Timestamp.valueOf("2022-06-26 01:05:09");
        address.setRegistStamp(timestamp);
        return address;
    }

    public List<Address> buildListAddressEntity(){
        List<Address>  data = new ArrayList<>();
        data.add(addressDataEntity());
        return data;
    }

    public Page<Address> buildPageAddressEntity(){
        Pageable pageable = PageRequest.of(1 , 1);
        Page<Address> data = new PageImpl<>(buildListAddressEntity(),pageable,buildListAddressEntity().size());
        return data;
    }
    public List<AddressDTO> buildListAddress(){
        List<AddressDTO> data = new ArrayList<>();
        data.add(addressData());
        return  data;
    }

    public RestResponsePage<Address> buildPageAddress(){
       return  new RestResponsePage<Address>(buildListAddressEntity(),1,1,1,1);
    }

    public Optional<User> buildOpUser(){
        Optional<User> data = Optional.of(UserData());
        return data;
    }
}
