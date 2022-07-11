package com.unicoin.customer.config.oauth2;

import com.unicoin.customer.entity.UserRole;
import com.unicoin.customer.repository.UserRepository;
import com.unicoin.customer.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        log.info("Username password: " + phoneNumber );
        //Kiem tra user co ton  tai khong
        Optional<com.unicoin.customer.entity.User> account = userRepository.findByPhoneNumber(phoneNumber);
        if (account.isEmpty()) throw new UsernameNotFoundException(phoneNumber);

        List<UserRole> userRoles = userRoleRepository.findAllByUser(account.get());
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if (userRoles.size() > 0){
            for (UserRole role :
                    userRoles) {
                authorityList.add(new SimpleGrantedAuthority(role.getRole().getRoleName().toUpperCase()));
            }
        }
        User user = new User(account.get().getPhoneNumber(), account.get().getPassword(), authorityList);
        log.info("User detail: {}", user);
        return user;
    }
}