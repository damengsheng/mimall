package com.qianfeng.xiaomi.cart.pojo;

public class CartUser {
    private int id;
   private int uid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "CartUser{" +
                "id=" + id +
                ", uid=" + uid +
                '}';
    }

    public CartUser(int id, int uid) {
        this.id = id;
        this.uid = uid;
    }

    public CartUser() {
    }
}
