package com.unicoin.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "roles", indexes = {
        @Index(name = "IDX1", columnList = "role_name")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long id;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "memo")
    private String memo;

    @Column(name = "regist_stamp", nullable = false)
    private Timestamp registStamp;

    @Column(name = "status", nullable = false)
    private Boolean status = false;

}