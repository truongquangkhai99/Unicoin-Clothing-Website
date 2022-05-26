package com.unicoin.customer.service.impl;

import com.unicoin.customer.entity.User;
import com.unicoin.customer.entity.UserRole;
import com.unicoin.customer.repository.UserRepository;
import com.unicoin.customer.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        //Kiem tra user co ton  tai khong
//        Optional<User> account = userRepository.findByPhoneNumber(phoneNumber);
//        if (account.isEmpty()) throw new UsernameNotFoundException(phoneNumber);
        User user1 = User.builder().phoneNumber("0333103855").password("$2a$10$Ex2t/uOBPytNH2iQPH5f9Ok3RdyyrSDIPvgMljaDkmg5K3zSAZk7m").build();
//        List<UserRole> userRoles = userRoleRepository.findAllByUserId(account.get());
        List<GrantedAuthority> authorityList = new ArrayList<>();
//        for (UserRole role :
//                userRoles) {
//            authorityList.add(new SimpleGrantedAuthority(role.getRole().getRoleName().toUpperCase()));
//        }
        authorityList.add(new SimpleGrantedAuthority("admin"));
        authorityList.add(new SimpleGrantedAuthority("user"));
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                user1.getPhoneNumber(),
                user1.getPassword(),
                authorityList
        );
        return user;
    }
}