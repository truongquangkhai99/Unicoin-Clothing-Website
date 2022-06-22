package emulator;

import com.unicoin.customer.common.RestResponsePage;
import com.unicoin.customer.dto.UserDTO;
import com.unicoin.customer.entity.Role;
import com.unicoin.customer.entity.User;
import com.unicoin.customer.form.AddCustomerForm;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
}
