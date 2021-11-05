package ua.com.alevel.Service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import ua.com.alevel.List.MyList;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Driver;
import ua.com.alevel.service.CarService;
import ua.com.alevel.service.DriverService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceTest {
    public static final String NAME_DRIVER = "PopaMyravia";
    public static final String PHONE_NUMBER = "+380662281337";
    private static final int CAR_SIZE = 16;
    MyList<Car> cars = new MyList<>();
    MyList<Driver> drivers = new MyList<>();
    CarService carService = new CarService();
    DriverService driverService = new DriverService();
    Car car = CarServiceTest.generateCar();

    @Test
    @Order(1)
    public void deleteCar(){
        CarService carService = new CarService();
        for(int i = 1; i < carService.findAllCars().getLength(); i++){
            carService.delete(i);
        }
        System.out.println(carService.findAllCars().getLength());
        Assertions.assertNotEquals(CAR_SIZE, carService.findAllCars().getLength());
    }

    @Test
    @Order(2)
    public void upCar(){
        CarService carService = new CarService();
        for(int i = 0; i < CAR_SIZE; i++){
            carService.create(car, NAME_DRIVER, PHONE_NUMBER);
        }
        Assertions.assertEquals(CAR_SIZE, carService.findAllCars().getLength());
    }

    @Test
    @Order(3)
    public void createCar(){
        Car car = CarServiceTest.generateCar("Hyinday");
        carService.create(car, NAME_DRIVER, PHONE_NUMBER);
        MyList<Car> cars = carService.findAllCars();
        Assertions.assertNotEquals(CAR_SIZE, cars.getLength());
    }

    @Test
    @Order(4)
    public void findCarByManufacture(){
        for(int i = 1; i < cars.length; i++){
            if(cars.get(i).getManufacture().equals("Hyinday")){
                Assertions.assertEquals(cars.get(i), "Hyinday");
            }
        }
    }

    @Test
    @Order(5)
    public void findDriverByName(){
        Driver driver = new Driver();
        driverService.create(driver);
        for(int i = 1; i < drivers.length; i++){
            if(drivers.get(i).getName().equals("Pipiska")){
                Assertions.assertEquals(drivers.get(i), "Pipiska");
            }
        }
    }
}
