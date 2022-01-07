package ua.com.alevel.Service;

import ua.com.alevel.persistence.entity.Driver;

public class DriverServiceTest {

    public static String NAME = "Artem";
    public static String PHONE_NUMBER = "+380669978722";

    public Driver generateDriver(){
        Driver driver = new Driver();
        driver.setName(NAME);
        driver.setPhoneNumber(PHONE_NUMBER);
        return driver;
    }

    public Driver generateDriver(String name){
        Driver driver = new Driver();
        driver.setName(name);
        driver.setPhoneNumber(PHONE_NUMBER);
        return driver;
    }

    public Driver generateDriver(String name, String phoneNumber){
        Driver driver = new Driver();
        driver.setName(name);
        driver.setPhoneNumber(phoneNumber);
        return driver;
    }
}
