package com.unicoin.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "roles")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "memo")
    private String memo;

    @Column(name = "regist_stamp", nullable = false)
    private Instant registStamp;

    @Column(name = "update_stamp", nullable = false)
    private Instant updateStamp;

    @Column(name = "status", nullable = false)
    private Boolean status = false;

    @Column(name = "update_user", nullable = false)
    private Integer updateUser;
}