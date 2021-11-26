package com.example.covihelp;
//
//import java.net.URL;
//
//public class HospitalStats {
//    private String name;
//    private String address;
//    private String area;
//    private String phoneNo;
//    private String charges;
//    private int bedsWithO2;
//    private int bedsWithVen;
//    private String mapLocation;
//    private boolean hasICU;
//    private boolean hasVen;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getArea() {
//        return area;
//    }
//
//    public void setArea(String area) {
//        this.area = area;
//    }
//
//    public String getPhoneNo() {
//        return phoneNo;
//    }
//
//    public void setPhoneNo(String phoneNo) {
//        this.phoneNo = phoneNo;
//    }
//
//    public String getCharges() {
//        return charges;
//    }
//
//    public void setCharges(String charges) {
//        this.charges = charges;
//    }
//
//    public int getBedsWithO2() {
//        return bedsWithO2;
//    }
//
//    public void setBedsWithO2(int bedsWithO2) {
//        this.bedsWithO2 = bedsWithO2;
//    }
//
//    public int getBedsWithVen() {
//        return bedsWithVen;
//    }
//
//    public void setBedsWithVen(int bedsWithVen) {
//        this.bedsWithVen = bedsWithVen;
//    }
//
//    public String getMapLocation() {
//        return mapLocation;
//    }
//
//    public void setMapLocation(String mapLocation) {
//        this.mapLocation = mapLocation;
//    }
//
//    public boolean isHasICU() {
//        return hasICU;
//    }
//
//    public void setHasICU(boolean hasICU) {
//        this.hasICU = hasICU;
//    }
//
//    public boolean isHasVen() {
//        return hasVen;
//    }
//
//    public void setHasVen(boolean hasVen) {
//        this.hasVen = hasVen;
//    }
//
//    public HospitalStats() {
//    }
//}

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class HospitalStats implements Serializable {

    private String district;
    private String area;
    private String hospitalCategory;
    private String hospitalName;
    private String hospitalAddress;
    private String hospitalPhone;
    private Long lastUpdatedOn;
    private String officerName;
    private String officerDesignation;
    private String charges;
    private String location;
    private int index;
    private int feeRegulatedBeds;
    private int totalBedsAllocatedToCovid;
    private int totalBedsWithoutOxygen;
    private int availableBedsWithoutOxygen;
    private int totalBedsWithOxygen;
    private int availableBedsWithOxygen;
    private int totalIcuBedsWithoutVentilator;
    private int availableIcuBedsWithoutVentilator;
    private int totalIcuBedsWithVentilator;
    private int availableIcuBedsWithVentilator;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public HospitalStats(String district, String hospitalCategory, String hospitalName, String hospitalAddress, String hospitalPhone, Long lastUpdatedOn, String officerName, String officerDesignation, String charges, int feeRegulatedBeds, int totalBedsAllocatedToCovid, int totalBedsWithoutOxygen, int availableBedsWithoutOxygen, int totalBedsWithOxygen, int availableBedsWithOxygen, int totalIcuBedsWithoutVentilator, int availableIcuBedsWithoutVentilator, int totalIcuBedsWithVentilator, int availableIcuBedsWithVentilator, String location,int index) {
        this.district = district;
        this.index = index;
        this.hospitalCategory = hospitalCategory;
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.hospitalPhone = hospitalPhone;
        this.lastUpdatedOn = lastUpdatedOn;
        this.officerName = officerName;
        this.officerDesignation = officerDesignation;
        this.charges = charges;
        this.feeRegulatedBeds = feeRegulatedBeds;
        this.totalBedsAllocatedToCovid = totalBedsAllocatedToCovid;
        this.totalBedsWithoutOxygen = totalBedsWithoutOxygen;
        this.availableBedsWithoutOxygen = availableBedsWithoutOxygen;
        this.totalBedsWithOxygen = totalBedsWithOxygen;
        this.availableBedsWithOxygen = availableBedsWithOxygen;
        this.totalIcuBedsWithoutVentilator = totalIcuBedsWithoutVentilator;
        this.availableIcuBedsWithoutVentilator = availableIcuBedsWithoutVentilator;
        this.totalIcuBedsWithVentilator = totalIcuBedsWithVentilator;
        this.availableIcuBedsWithVentilator = availableIcuBedsWithVentilator;
        this.location = location;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "HospitalStats{" +
                "district='" + district + '\'' +
                ", area='" + area + '\'' +
                ", hospitalCategory='" + hospitalCategory + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", hospitalAddress='" + hospitalAddress + '\'' +
                ", hospitalPhone='" + hospitalPhone + '\'' +
                ", lastUpdatedOn=" + lastUpdatedOn +
                ", officerName='" + officerName + '\'' +
                ", officerDesignation='" + officerDesignation + '\'' +
                ", charges='" + charges + '\'' +
                ", feeRegulatedBeds=" + feeRegulatedBeds +
                ", totalBedsAllocatedToCovid=" + totalBedsAllocatedToCovid +
                ", totalBedsWithoutOxygen=" + totalBedsWithoutOxygen +
                ", availableBedsWithoutOxygen=" + availableBedsWithoutOxygen +
                ", totalBedsWithOxygen=" + totalBedsWithOxygen +
                ", availableBedsWithOxygen=" + availableBedsWithOxygen +
                ", totalIcuBedsWithoutVentilator=" + totalIcuBedsWithoutVentilator +
                ", availableIcuBedsWithoutVentilator=" + availableIcuBedsWithoutVentilator +
                ", totalIcuBedsWithVentilator=" + totalIcuBedsWithVentilator +
                ", availableIcuBedsWithVentilator=" + availableIcuBedsWithVentilator +
                '}';
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHospitalCategory() {
        return hospitalCategory;
    }

    public void setHospitalCategory(String hospitalCategory) {
        this.hospitalCategory = hospitalCategory;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getHospitalPhone() {
        return hospitalPhone;
    }

    public void setHospitalPhone(String hospitalPhone) {
        this.hospitalPhone = hospitalPhone;
    }

    public Long getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Long lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public String getOfficerName() {
        return officerName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public String getOfficerDesignation() {
        return officerDesignation;
    }

    public void setOfficerDesignation(String officerDesignation) {
        this.officerDesignation = officerDesignation;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public int getFeeRegulatedBeds() {
        return feeRegulatedBeds;
    }

    public void setFeeRegulatedBeds(int feeRegulatedBeds) {
        this.feeRegulatedBeds = feeRegulatedBeds;
    }

    public int getTotalBedsAllocatedToCovid() {
        return totalBedsAllocatedToCovid;
    }

    public void setTotalBedsAllocatedToCovid(int totalBedsAllocatedToCovid) {
        this.totalBedsAllocatedToCovid = totalBedsAllocatedToCovid;
    }

    public int getTotalBedsWithoutOxygen() {
        return totalBedsWithoutOxygen;
    }

    public void setTotalBedsWithoutOxygen(int totalBedsWithoutOxygen) {
        this.totalBedsWithoutOxygen = totalBedsWithoutOxygen;
    }

    public int getAvailableBedsWithoutOxygen() {
        return availableBedsWithoutOxygen;
    }

    public void setAvailableBedsWithoutOxygen(int availableBedsWithoutOxygen) {
        this.availableBedsWithoutOxygen = availableBedsWithoutOxygen;
    }

    public int getTotalBedsWithOxygen() {
        return totalBedsWithOxygen;
    }

    public void setTotalBedsWithOxygen(int totalBedsWithOxygen) {
        this.totalBedsWithOxygen = totalBedsWithOxygen;
    }

    public int getAvailableBedsWithOxygen() {
        return availableBedsWithOxygen;
    }

    public void setAvailableBedsWithOxygen(int availableBedsWithOxygen) {
        this.availableBedsWithOxygen = availableBedsWithOxygen;
    }

    public int getTotalIcuBedsWithoutVentilator() {
        return totalIcuBedsWithoutVentilator;
    }

    public void setTotalIcuBedsWithoutVentilator(int totalIcuBedsWithoutVentilator) {
        this.totalIcuBedsWithoutVentilator = totalIcuBedsWithoutVentilator;
    }

    public int getAvailableIcuBedsWithoutVentilator() {
        return availableIcuBedsWithoutVentilator;
    }

    public void setAvailableIcuBedsWithoutVentilator(int availableIcuBedsWithoutVentilator) {
        this.availableIcuBedsWithoutVentilator = availableIcuBedsWithoutVentilator;
    }

    public int getTotalIcuBedsWithVentilator() {
        return totalIcuBedsWithVentilator;
    }

    public void setTotalIcuBedsWithVentilator(int totalIcuBedsWithVentilator) {
        this.totalIcuBedsWithVentilator = totalIcuBedsWithVentilator;
    }

    public int getAvailableIcuBedsWithVentilator() {
        return availableIcuBedsWithVentilator;
    }

    public void setAvailableIcuBedsWithVentilator(int availableIcuBedsWithVentilator) {
        this.availableIcuBedsWithVentilator = availableIcuBedsWithVentilator;
    }

}



