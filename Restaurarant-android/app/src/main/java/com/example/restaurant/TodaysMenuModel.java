package com.example.restaurant;

public class TodaysMenuModel {

    private  String item_id,item_name,item_type,item_description,item_rate;

    public TodaysMenuModel(String item_id, String item_name, String item_type, String item_description, String item_rate) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_type = item_type;
        this.item_description = item_description;
        this.item_rate = item_rate;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_rate() {
        return item_rate;
    }

    public void setItem_rate(String item_rate) {
        this.item_rate = item_rate;
    }
}
