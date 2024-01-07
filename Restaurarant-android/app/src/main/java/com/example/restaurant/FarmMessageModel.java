package com.example.restaurant;

public class FarmMessageModel {

    private String farm_reg_id,login_id,message,reply;

    public FarmMessageModel(String farm_reg_id, String login_id, String message, String reply) {
        this.farm_reg_id = farm_reg_id;
        this.login_id = login_id;
        this.message = message;
        this.reply = reply;
    }

    public String getFarm_reg_id() {
        return farm_reg_id;
    }

    public void setFarm_reg_id(String farm_reg_id) {
        this.farm_reg_id = farm_reg_id;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
