package com.unicoin.product.entity;

import javax.persistence.*;

@Entity
@Table(name = "size_id")
public class SizeId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id", nullable = false)
    private Integer id;

    @Column(name = "size_name", nullable = false, length = 45)
    private String sizeName;

    @Column(name = "regist_stamp", nullable = false, length = 45)
    private String registStamp;

    @Column(name = "update_user", nullable = false, length = 45)
    private String updateUser;

    @Column(name = "status", nullable = false)
    private Boolean status = false;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getRegistStamp() {
        return registStamp;
    }

    public void setRegistStamp(String registStamp) {
        this.registStamp = registStamp;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}