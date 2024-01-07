package com.example.restaurant;

public class Model_Update {

    String dishname,categorys,quantity;
    public Model_Update(String dishname,String categorys,String quantity) {

        this.dishname = dishname;
        this.categorys=categorys;
        this.quantity=quantity;
    }



    public String getDishname() {
        return dishname;
    }
    public String getCategorys() {
        return categorys;
    }
    public String getQuantity(){
        return quantity;}

}
