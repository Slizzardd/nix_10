package ua.com.alevel.Service;

import org.junit.Test;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Driver;

public class CarServiceTest {
    private static final String MANUFACTURE = "Hyinday";
    private static final String BRAND = "Elantra";
    private static final int YEARS_OF_ISSUE = 2021;
    public static Car generateCar(){
        Car car = new Car();
        car.setManufacture(MANUFACTURE);
        car.setBrand(BRAND);
        car.setYearOfIssue(YEARS_OF_ISSUE);
        return car;
    }

    public static Car generateCar(String manufacture){
        Car car = new Car();
        car.setManufacture(manufacture);
        car.setBrand(BRAND);
        car.setYearOfIssue(YEARS_OF_ISSUE);
        return car;
    }

    public static Car generateCar(String manufacture, String brand){
        Car car = new Car();
        car.setManufacture(manufacture);
        car.setBrand(brand);
        car.setYearOfIssue(YEARS_OF_ISSUE);
        return car;
    }

    public static Car generateCar(String manufacture, String brand, int yearsOfIssue){
        Car car = new Car();
        car.setManufacture(manufacture);
        car.setBrand(brand);
        car.setYearOfIssue(yearsOfIssue);
        return car;
    }

    private static Driver generateDriver(String name){
        Driver driver = new Driver();
        driver.setName(name);
        return driver;
    }
}
