package com.bankfinder.chathurangasandun.bankfinder.model;

/**
 * Created by Chathuranga Sandun on 4/14/2016.
 */
public class Branches {

    private int id;
    private String bank;
    private String name;
    private double latitude;
    private double longtitude;
    private String address;
    private String tp;
    private String weekOpen;
    private String weekClose;
    private String satOpen;
    private String satClose;


    public Branches(int id, String bank, String name, double latitude, double longtitude, String address, String tp, String weekOpen, String weekClose, String satOpen, String satClose) {
        this.id = id;
        this.bank = bank;
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.address = address;
        this.tp = tp;
        this.weekOpen = weekOpen;
        this.weekClose = weekClose;
        this.satOpen = satOpen;
        this.satClose = satClose;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getWeekOpen() {
        return weekOpen;
    }

    public void setWeekOpen(String weekOpen) {
        this.weekOpen = weekOpen;
    }

    public String getWeekClose() {
        return weekClose;
    }

    public void setWeekClose(String weekClose) {
        this.weekClose = weekClose;
    }

    public String getSatOpen() {
        return satOpen;
    }

    public void setSatOpen(String satOpen) {
        this.satOpen = satOpen;
    }

    public String getSatClose() {
        return satClose;
    }

    public void setSatClose(String satClose) {
        this.satClose = satClose;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
