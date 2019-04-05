package com.qianfeng.xiaomi.order.pojo;

public class Order_Address {
    private String oid;
    private int aid;

    public Order_Address() {
    }

    public Order_Address(String oid, int aid) {
        this.oid = oid;
        this.aid = aid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }
}
