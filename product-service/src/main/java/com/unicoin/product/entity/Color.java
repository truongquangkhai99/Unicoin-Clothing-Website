package com.unicoin.product.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "colors")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id", nullable = false)
    private Integer id;

    @Column(name = "color_name", nullable = false, length = 105)
    private String colorName;

    @Column(name = "regist_stamp", nullable = false)
    private Instant registStamp;

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

    public Instant getRegistStamp() {
        return registStamp;
    }

    public void setRegistStamp(Instant registStamp) {
        this.registStamp = registStamp;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}