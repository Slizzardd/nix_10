package ua.com.alevel.db;

import ua.com.alevel.List.MyList;
import ua.com.alevel.entity.Car;
import ua.com.alevel.entity.Driver;


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

    public void create(Car car, String nameDriver, String phoneNumberDriver) {
        if(drivers.getSizeList() != 0){
            car.setSerialNumber(generateId(Entity.CAR));
            car.setIdDrivers(getIdDriversByNameAndPhoneNumber(nameDriver, phoneNumberDriver));
            cars.add(car);
        }else{
            car.setSerialNumber(generateId(Entity.CAR));
            car.setIdDrivers(0);
            cars.add(car);
        }
    }

    public void create(Driver driver) {
        driver.setId(generateId(Entity.DRIVER));
        drivers.add(driver);
    }

    public void update(Car car) throws NullPointerException{
        Car current = findBySerialNumber(car.getSerialNumber());
        if (current != null) {
            current.setManufacture(car.getManufacture());
            current.setBrand(car.getBrand());
            current.setYearOfIssue(car.getYearOfIssue());
            current.setIdDrivers(car.getIdDrivers());
        }
    }

    public void updateWithoutDriverId(Car car, String nameDriver, String phoneNumberDriver) throws NullPointerException{
        for(int i = 0; i < cars.length; i++){
            if(cars.get(i).equals(car)){
                Car current = cars.get(i);
                current.setIdDrivers(getIdDriversByNameAndPhoneNumber(nameDriver, phoneNumberDriver));
                update(current);
                break;
            }
        }
    }

    public void update(Driver driver) throws NullPointerException{
        Driver current = findById(driver.getId());
        if (current != null) {
            current.setName(driver.getName());
            current.setPhoneNumber(driver.getPhoneNumber());
        }
    }

    public void delete(int id, Entity entity) throws NullPointerException{
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

    public Driver findById(int id) throws NullPointerException{
            for (int i = 0; i < drivers.length; i++) {
                if (drivers.get(i).getId() == id) {
                    return drivers.get(i);
                }
            }
        return null;
    }

    public Car findBySerialNumber(int serialNumber) throws NullPointerException{
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

    public int getIdDriversByNameAndPhoneNumber(String name, String phoneNumber) {
        try {
            for (int i = 0; i < drivers.getLength(); i++) {
                if (Objects.equals(drivers.get(i).getName(), name) && Objects.equals(drivers.get(i).getPhoneNumber(), phoneNumber)) {
                    return drivers.get(i).getId();
                }
            }
        } catch (NullPointerException e) {
            return 0;
        }
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
