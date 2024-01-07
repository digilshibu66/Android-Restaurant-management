package com.example.restaurant;

import com.android.volley.toolbox.StringRequest;

public class FeedbackModel {

    private String feedback_id,customer_login_id,name,feedback,reply;

    public FeedbackModel(String feedback_id, String customer_login_id,String name, String feedback, String reply) {
        this.feedback_id = feedback_id;
        this.customer_login_id = customer_login_id;
        this.name=name;
        this.feedback = feedback;
        this.reply = reply;
    }

    public String getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(String feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getCustomer_login_id() {
        return customer_login_id;
    }

    public void setCustomer_login_id(String customer_login_id) {
        this.customer_login_id = customer_login_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
