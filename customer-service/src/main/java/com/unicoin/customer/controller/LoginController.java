package com.unicoin.customer.controller;

import com.unicoin.customer.common.JwtRequest;
import com.unicoin.customer.common.JwtResponse;
import com.unicoin.customer.common.JwtUtility;
import com.unicoin.customer.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("")
    public JwtResponse postLogin(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getPhoneNumber(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
            e.printStackTrace();
        }

        final UserDetails userDetails
                = userDetailService.loadUserByUsername(jwtRequest.getPhoneNumber());
        final String token =
                jwtUtility.generateToken(userDetails);

        return JwtResponse.builder().accessToken(token).build();
    }
}
