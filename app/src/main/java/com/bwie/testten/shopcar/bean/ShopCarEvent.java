package com.bwie.testten.shopcar.bean;


public class ShopCarEvent {
    float price;
    int position;

    public ShopCarEvent(float price, int position) {
        this.price = price;
        this.position = position;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
