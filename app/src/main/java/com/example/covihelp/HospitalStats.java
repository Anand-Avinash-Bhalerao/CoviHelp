package com.example.covihelp;

import java.net.URL;

public class HospitalStats {
    private String name;
    private String address;
    private String area;
    private String phoneNo;
    private String charges;
    private int bedsWithO2;
    private int bedsWithVen;
    private String mapLocation;
    private boolean hasICU;
    private boolean hasVen;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public int getBedsWithO2() {
        return bedsWithO2;
    }

    public void setBedsWithO2(int bedsWithO2) {
        this.bedsWithO2 = bedsWithO2;
    }

    public int getBedsWithVen() {
        return bedsWithVen;
    }

    public void setBedsWithVen(int bedsWithVen) {
        this.bedsWithVen = bedsWithVen;
    }

    public String getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(String mapLocation) {
        this.mapLocation = mapLocation;
    }

    public boolean isHasICU() {
        return hasICU;
    }

    public void setHasICU(boolean hasICU) {
        this.hasICU = hasICU;
    }

    public boolean isHasVen() {
        return hasVen;
    }

    public void setHasVen(boolean hasVen) {
        this.hasVen = hasVen;
    }

    public HospitalStats() {
    }
}
