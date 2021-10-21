package ua.com.alevel.entity;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class User {

    //car
    private String manufacturer;
    private String brand;
    private int yearOfIssue;
    private String serialNumber;

    //user
    private String name;
    private String phoneNumber;
    private String email;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        if (StringUtils.isEmpty(manufacturer)) {
            this.manufacturer = "Не определенно";
        } else {
            this.manufacturer = manufacturer;
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (StringUtils.isEmpty(brand)) {
            this.brand = "Не определенно";
        } else {
            this.brand = brand;
        }
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        if (StringUtils.isEmpty(String.valueOf(yearOfIssue))) {
            this.yearOfIssue = 0;
        } else {
            this.yearOfIssue = yearOfIssue;
        }
    }

    public String getSerialNumber() {
        if(!StringUtils.isEmpty(serialNumber)){
            return serialNumber;
        }else{
            throw new RuntimeException("ВВЕДИТЕ СЕРИЙНЫЙ НОМЕР!!!");
        }
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isEmpty(name)) {
            this.name = "Не определенно";
        } else {

            this.name = name;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)) {
            this.phoneNumber = "Не определенно";
        } else {
            this.phoneNumber = phoneNumber;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            this.email = "Не определенно";
        } else {
            this.email = email;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", cars ='" + manufacturer + " " + brand + " " + yearOfIssue + "гг. " + '\'' +
                ", serial number ='" + serialNumber + '\'' +
                ", phone number=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }
}
