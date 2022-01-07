package ua.com.alevel.task1.controller;

import ua.com.alevel.List.MyList;
import ua.com.alevel.persistence.entity.Car;
import ua.com.alevel.task1.service.CarService;

import java.io.IOException;

import static ua.com.alevel.UtilityHelper.UtilityHelper.*;

public class CarController {
    private static CarController instance;
    private final CarService carService = new CarService();

    public static CarController getInstance() {
        if (instance == null) {
            instance = new CarController();
        }
        return instance;
    }

    public void run() {
        print("Select an action relative to the car:");
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
        print("Car");
        print("If you want to create a new car, press 1");
        print("If you want to update the car, press 2");
        print("If you want to delete the car, press 3");
        print("If you want to find a car by car serial number, press 4");
        print("If you want to get a list of all car, press 5");
        print("If you want to get a list of all car without driver id, press 6");
        print("If you want to update the car without idDriver, press 7");
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
            case "4" -> findBySerialNumber();
            case "5" -> findAll();
            case "6" -> findAllWithoutDriverId();
            case "7" -> updateWithoutDriverId();
        }
    }

    private void create() {
        print("Create car: ");
        print("Enter the manufacture car: ");
        String manufacture = getString();
        print("Enter the brand car: ");
        String brand = getString();
        print("Enter the years of issue car: ");
        int yearsOfIssue = getInt();
        print("Enter the name driver: ");
        String nameDriver = getString();
        print("Enter the phone number driver: ");
        String phoneNumberDriver = getString();
        Car car = new Car();
        car.setManufacture(manufacture);
        car.setBrand(brand);
        car.setYearOfIssue(yearsOfIssue);
        carService.create(car, nameDriver, phoneNumberDriver);
    }

    private void updateWithoutDriverId() {
        print("update car without driver id");
        Car car = findCarWithoutDriverId();
        if (car != null) {
            print("Car: " + car);
            print("Enter the name driver: ");
            String name = getString();
            print("Enter the phone number driver: ");
            String phoneNumber = getString();
            try {
                if (car != null) {
                    carService.updateWithoutDriverId(car, name, phoneNumber);
                }
            } catch (NullPointerException e) {
                print("problem" + e.getMessage());
            }
        } else {
            print("All created cars have a driver id");
        }
    }


    private void update() {
        print("Update car");
        print("Enter the id serial number car: ");
        int serialNumber = getInt();
        try {
            if (carService.findCarBySerialNumber(serialNumber) == null) {
                throw new NullPointerException();
            }
            print("Enter the manufacture car: ");
            String manufacture = getString();
            print("Enter the brand car: ");
            String brand = getString();
            print("Enter the years of issue car: ");
            int yearsOfIssue = getInt();

            Car car = new Car();
            car.setSerialNumber(serialNumber);
            car.setManufacture(manufacture);
            car.setBrand(brand);
            car.setYearOfIssue(yearsOfIssue);
            carService.update(car);
        } catch (NullPointerException e) {
            print("There is no such car");
        }
    }

    private void delete() {
        print("delete car");
        print("Enter the serial number(VIN): ");
        int serialNumber = getInt();
        try {
            carService.delete(serialNumber);
        } catch (NullPointerException e) {
            print("There is no such car");
        }
    }

    private void findBySerialNumber() {
        print("find by serial number: ");
        print("Enter the serial number(VIN): ");
        int serialNumber = getInt();
        try {
            Car car = carService.findCarBySerialNumber(serialNumber);
            if (car != null) {
                print("Car: " + car);
            }
        } catch (NullPointerException e) {
            print("There is no such car");
        }
    }

    private void findAll() {
        print("find all cars: ");
        MyList<Car> cars = carService.findAllCars();
        for (int i = 0; i < cars.getLength(); i++) {
            if (cars.get(i) != null) {
                print("Cars: " + cars.get(i));
            }
        }

    }

    private Car findCarWithoutDriverId() {
        MyList<Car> cars = carService.findAllCars();
        for (int i = 0; i < cars.getLength(); i++) {
            if (cars.get(i) != null && cars.get(i).getIdDrivers() == 0) {
                return cars.get(i);
            }
        }
        return null;
    }

    private void findAllWithoutDriverId() {
        print("find all without driver id: ");
        MyList<Car> cars = carService.findAllCars();
        for (int i = 0; i < cars.getLength(); i++) {
            if (cars.get(i) != null && cars.get(i).getIdDrivers() == 0) {
                print("Cars: " + cars.get(i));
            }
        }
    }
}
