package com.unicoin.customer.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "roles")
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

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Instant getUpdateStamp() {
        return updateStamp;
    }

    public void setUpdateStamp(Instant updateStamp) {
        this.updateStamp = updateStamp;
    }

    public Instant getRegistStamp() {
        return registStamp;
    }

    public void setRegistStamp(Instant registStamp) {
        this.registStamp = registStamp;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}