package ua.com.alevel.controller;

import ua.com.alevel.List.MyList;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Driver;
import ua.com.alevel.service.CarService;
import ua.com.alevel.service.DriverService;

import java.io.IOException;

import static ua.com.alevel.UtilityHelper.UtilityHelper.*;

public class DriverController {
    private static DriverController instance;

    private final DriverService driverService = new DriverService();

    public static DriverController getInstance() {
        if (instance == null) {
            instance = new DriverController();
        }
        return instance;
    }

    public void run() {
        print("Select an action relative to the driver:");
        String position;
        try {
            runNavigation();
            while ((position = getString()) != null) {
                crud(position);
                position = getString();
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position);
            }
        } catch (IOException e) {
            System.out.println("problems: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        print("Driver:");
        print("If you want to create a new driver, press 1");
        print("If you want to update the driver, press 2");
        print("If you want to delete the driver, press 3");
        print("If you want to find a driver by id, press 4");
        print("If you want to get a list of all drivers, press 5");
        print("If you want to return to the menu, press 0");
        print("");
    }

    public void crud(String position) throws IOException {
        print("Choose operation: ");
        runNavigation();
        String line = getString();
        switch (line) {
            case "1" -> create();
            case "2" -> update();
            case "3" -> delete();
            case "4" -> findById();
            case "5" -> findAll();
        }
    }

    private void create() {
        print("create driver");
        print("Enter the name: ");
        String name = getString();
        print("Enter the phone number: ");
        String phoneNumber = getString();

        Driver driver = new Driver();
        driver.setName(name);
        driver.setPhoneNumber(phoneNumber);
        driverService.create(driver);
    }

    private void update() {
        print("update driver");
        print("Enter the id: ");
        int id = getInt();
        print("Enter the name: ");
        String name = getString();
        print("Enter the phone number: ");
        String phoneNumber = getString();

        Driver driver = new Driver();
        driver.setId(id);
        driver.setName(name);
        driver.setPhoneNumber(phoneNumber);
        driverService.update(driver);
    }

    private void delete() {
        print("delete driver");
        print("Enter the id driver: ");
        int id = getInt();

        driverService.delete(id);
    }

    private void findById() {
        print("find driver by id");
        print("Enter the id driver: ");
        int id = getInt();

        Driver driver = driverService.findDriverById(id);
        print("Driver: " + driver);
    }

    private void findAll() {
        print("find all drivers");
        MyList<Driver> drivers = driverService.findAllDrivers();
        for (int i = 0; i < drivers.getLength(); i++) {
            if (drivers.get(i) != null) {
                print("Driver: " + drivers.get(i));
            }
        }
    }

    public void outputDriverAndCar() {
        MyList<Driver> drivers = driverService.findAllDrivers();
        CarService carService = new CarService();
        MyList<Car> cars = carService.findAllCars();
        for (int i = 0; i < drivers.getLength(); i++) {
            if (drivers.get(i) != null) {
                print("Driver: " + drivers.get(i));
                for (int q = 0; q < cars.getLength(); q++) {
                    if (cars.get(q) != null) {
                        if (drivers.get(i).getId() == cars.get(q).getIdDrivers()) {
                            print("Cars driver: " + cars.get(q));
                        }
                    }
                }
            }
            print("");
        }
    }
}
