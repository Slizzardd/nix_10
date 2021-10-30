package ua.com.alevel.db;

import ua.com.alevel.entity.Car;

import java.util.Scanner;

public class CarDb extends DriverDb{

    public  Car[] cars = new Car[5];
    private static CarDb instance;
    private int carsSize = 0;
    static Scanner scanner = new Scanner(System.in);

    public static CarDb getInstance() {
        if (instance == null) {
            instance = new CarDb();
        }
        return instance;
    }

    public void create(Car car){
        car.setIdOwner(userIdByNameAndPhoneNumber());
        car.setSerialNumber(generateSerialNumber());
        copy(car);
        carsSize++;
    }

    private void copy(Car car) {
        cars[carsSize] = car;
        if (carsSize == cars.length - 1) {
            cars[carsSize] = car;
            Car[] tempUser = new Car[cars.length + 5];
            System.arraycopy(cars, 0, tempUser, 0, cars.length);
            cars = tempUser;
        }
    }

    private String generateSerialNumber() {
        System.out.println("Введите серийный номер машины(VIN): ");
        String serialNumber = scanner.nextLine();
        for (Car car : cars) {
            if (car != null && car.getSerialNumber().equals(serialNumber)) {
                return generateSerialNumber();
            }
        }
        return serialNumber;
    }

    public void update(Car car){
        Car current = findBySerialNumber(car.getSerialNumber());
        current.setManufacturer(car.getManufacturer());
        current.setBrand(car.getBrand());
        current.setAge(car.getAge());
        current.setIdOwner(userIdByNameAndPhoneNumber());
    }

    public void delete(String serialNumber) {
        for (int i = 0; i < cars.length; i++) {
            if (serialNumber.equals(cars[i].getSerialNumber())) {
                for (int p = i; p < cars.length; p++) {
                    if (p != cars.length - 1) {
                        cars[p] = cars[p + 1];
                    } else {
                        cars[p] = null;
                    }
                }
                break;
            }
        }
    }

    public void deleteByIdOwner(String idOwner) {
        for (int i = 0; i < cars.length; i++) {
            if (idOwner.equals(cars[i].getIdOwner())) {
                for (int p = i; p < cars.length; p++) {
                    if (p != cars.length - 1) {
                        cars[p] = cars[p + 1];
                    } else {
                        cars[p] = null;
                    }
                }
                break;
            }
        }
    }

    public Car findBySerialNumber(String serialNumber) {
        for (Car user : cars) {
            if (serialNumber.equals(user.getSerialNumber())) {
                return user;
            }
        }
        throw new RuntimeException("Такого серийного номера нет");
    }

    public Car[] findAllCar() {
        return cars;
    }
}
