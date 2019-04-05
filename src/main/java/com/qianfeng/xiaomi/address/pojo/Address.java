package com.qianfeng.xiaomi.address.pojo;

import javax.validation.constraints.NotEmpty;

public class Address {
    private int id;
    @NotEmpty(message = "地址不能为空")
    private String detail;
    @NotEmpty(message = "收件人名字不能为空")
    private String name;
    @NotEmpty(message = "收件人电话不能为空")
    private String phone;
    private int uid;
    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", uid=" + uid +
                ", level=" + level +
                '}';
    }
}
