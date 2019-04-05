package com.qianfeng.xiaomi.order.pojo;

import java.util.Date;

public class Order {
    private String id;
    private int uid;
    private double money;
    private String status;
    private Date time;

    private String address;

    public Order() {
    }

    public Order(String id, int uid, double money, String status, Date time, String address) {
        this.id = id;
        this.uid = uid;
        this.money = money;
        this.status = status;
        this.time = time;
        this.address = address;
    }

    public Order(String id, int uid, double money, String status, Date time) {
        this.id = id;
        this.uid = uid;
        this.money = money;
        this.status = status;
        this.time = time;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
