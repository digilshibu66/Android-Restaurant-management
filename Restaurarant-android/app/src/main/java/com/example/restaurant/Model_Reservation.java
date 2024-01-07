package com.example.restaurant;

public class Model_Reservation {

    private String table_id,table_no,seats,status;

    public Model_Reservation(String table_id, String table_no, String seats, String status) {
        this.table_id = table_id;
        this.table_no = table_no;
        this.seats = seats;
        this.status = status;
    }

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public String getTable_no() {
        return table_no;
    }

    public void setTable_no(String table_no) {
        this.table_no = table_no;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
