package ua.com.alevel.db;

import ua.com.alevel.List.MyList;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Driver;

import static ua.com.alevel.UtilityHelper.UtilityHelper.*;

import java.util.Objects;
import java.util.Random;

public class CarDriverDb {
    private static CarDriverDb instance;
    private final MyList<Car> cars;
    private final MyList<Driver> drivers;

    private CarDriverDb() {
        cars = new MyList<>();
        drivers = new MyList<>();
    }

    public static CarDriverDb getInstance() {
        if (instance == null) {
            instance = new CarDriverDb();
        }
        return instance;
    }

    public void create(Car car) {
        car.setSerialNumber(generateId(Entity.CAR));
        car.setIdDrivers(getIdDriversByNameAndPhoneNumber());
        cars.add(car);
    }

    public void create(Driver driver) {
        driver.setId(generateId(Entity.DRIVER));
        drivers.add(driver);
    }

    public void update(Car car) {
        Car current = findBySerialNumber(car.getSerialNumber());
        current.setManufacture(car.getManufacture());
        current.setBrand(car.getBrand());
        current.setYearOfIssue(car.getYearOfIssue());

    }

    public void update(Driver driver) {
        Driver current = findById(driver.getId());
        current.setName(driver.getName());
        current.setPhoneNumber(driver.getPhoneNumber());
    }

    public void delete(int id, Entity entity) {
        switch (entity) {
            case CAR -> {
                for (int i = 0; i < cars.length; i++) {
                    if (cars.get(i).getSerialNumber() == id) {
                        cars.remove(i);
                        break;
                    }
                }
            }
            case DRIVER -> {
                for (int i = 0; i < drivers.length; i++) {
                    if (drivers.get(i).getId() == id) {
                        drivers.remove(i);
                        deleteByIdDrivers(id);
                        break;
                    }
                }
            }
        }
    }

    private void deleteByIdDrivers(int id) {
        for (int i = 0; i < cars.getLength(); i++) {
            if (cars.get(i) != null) {
                if (cars.get(i).getIdDrivers() == id) {
                    cars.remove(i);
                    i--;
                }
            }
        }
    }

    public Driver findById(int id) {
        for (int i = 0; i < drivers.length; i++) {
            if (drivers.get(i).getId() == id) {
                return drivers.get(i);
            }
        }
        return null;
    }

    public Car findBySerialNumber(int serialNumber) {
        for (int i = 0; i < cars.length; i++) {
            if (cars.get(i).getSerialNumber() == serialNumber) {
                return cars.get(i);
            }
        }
        return null;
    }

    public MyList<Driver> findAllDrivers() {
        return drivers;
    }

    public MyList<Car> findAllCar() {
        return cars;
    }

    public int getIdDriversByNameAndPhoneNumber() {
        print("Enter the name driver: ");
        String name = getString();
        print("Enter the phone number driver: ");
        String phoneNumber = getString();
        for (int i = 0; i < drivers.getLength(); i++) {
            if (Objects.equals(drivers.get(i).getName(), name) && Objects.equals(drivers.get(i).getPhoneNumber(), phoneNumber)) {
                return drivers.get(i).getId();
            }
        }
        getIdDriversByNameAndPhoneNumber();
        return 0;
    }

    private int generateId(Entity entity) {
        int serialNumber = new Random().nextInt();
        switch (entity) {
            case CAR -> {
                for (int i = 0; i < cars.length; i++) {
                    if (cars.get(i) == null) continue;
                    if (cars.get(i).getSerialNumber() == serialNumber) return generateId(Entity.CAR);
                }
            }
            case DRIVER -> {
                for (int i = 0; i < drivers.length; i++) {
                    if (drivers.get(i) == null) continue;
                    if (drivers.get(i).getSerialNumber() == serialNumber) return generateId(Entity.DRIVER);
                }
            }
        }
        return serialNumber;
    }

    public enum Entity {
        DRIVER, CAR
    }
}
