package com.unicoin.customer.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumer;

    @Column(name = "fullname", nullable = false, length = 45)
    private String fullname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "regist_stamp", nullable = false)
    private Instant registStamp;

    @Column(name = "update_stamp", nullable = false)
    private Instant updateStamp;

    @Column(name = "status", nullable = false)
    private Boolean status = false;

}