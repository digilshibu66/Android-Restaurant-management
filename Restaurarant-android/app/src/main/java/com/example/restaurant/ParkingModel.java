package com.example.restaurant;

public class ParkingModel {

    private String parkingid,secloginid,totalcar,totalbike,parkingdate;

    public ParkingModel(String parkingid, String secloginid, String totalcar, String totalbike, String parkingdate) {
        this.parkingid = parkingid;
        this.secloginid = secloginid;
        this.totalcar = totalcar;
        this.totalbike = totalbike;
        this.parkingdate = parkingdate;
    }

    public String getParkingid() {
        return parkingid;
    }

    public void setParkingid(String parkingid) {
        this.parkingid = parkingid;
    }

    public String getSecloginid() {
        return secloginid;
    }

    public void setSecloginid(String secloginid) {
        this.secloginid = secloginid;
    }

    public String getTotalcar() {
        return totalcar;
    }

    public void setTotalcar(String totalcar) {
        this.totalcar = totalcar;
    }

    public String getTotalbike() {
        return totalbike;
    }

    public void setTotalbike(String totalbike) {
        this.totalbike = totalbike;
    }

    public String getParkingdate() {
        return parkingdate;
    }

    public void setParkingdate(String parkingdate) {
        this.parkingdate = parkingdate;
    }
}
