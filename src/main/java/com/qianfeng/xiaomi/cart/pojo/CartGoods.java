package com.qianfeng.xiaomi.cart.pojo;

import com.qianfeng.xiaomi.goods.pojo.Goods;

public class CartGoods {
    private int cid;
    private int gid;
    private int num;

    //小计=数量*价格
    private double money;

    private Goods goods;

    public CartGoods(int cid, int gid, int num) {
        this.cid = cid;
        this.gid = gid;
        this.num = num;
    }

    public double getMoney() {
        return num * goods.getPrice();
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }
}
