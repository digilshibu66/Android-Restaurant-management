package com.example.restaurant;

public class OnlineOrderModel {

    private String order_id,item_id,login_id,ordered_item,ordered_price,ordered_quantity;

    public OnlineOrderModel(String order_id, String item_id, String login_id, String ordered_item, String ordered_price, String ordered_quantity) {
        this.order_id = order_id;
        this.item_id = item_id;
        this.login_id = login_id;
        this.ordered_item = ordered_item;
        this.ordered_price = ordered_price;
        this.ordered_quantity = ordered_quantity;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getOrdered_item() {
        return ordered_item;
    }

    public void setOrdered_item(String ordered_item) {
        this.ordered_item = ordered_item;
    }

    public String getOrdered_price() {
        return ordered_price;
    }

    public void setOrdered_price(String ordered_price) {
        this.ordered_price = ordered_price;
    }

    public String getOrdered_quantity() {
        return ordered_quantity;
    }

    public void setOrdered_quantity(String ordered_quantity) {
        this.ordered_quantity = ordered_quantity;
    }
}
