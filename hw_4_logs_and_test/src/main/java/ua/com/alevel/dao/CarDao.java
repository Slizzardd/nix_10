package ua.com.alevel.dao;

import ua.com.alevel.entity.Car;
import ua.com.alevel.db.CarDriverDb;
import ua.com.alevel.List.MyList;

public class CarDao {

    public void create(Car car, String nameDriver, String phoneNumberDriver) {
        CarDriverDb.getInstance().create(car, nameDriver, phoneNumberDriver);
    }

    public void update(Car car) {
        CarDriverDb.getInstance().update(car);
    }

    public void delete(int serialNumber) {
        CarDriverDb.getInstance().delete(serialNumber, CarDriverDb.Entity.CAR);
    }

    public void updateWithoutDriverId(Car car, String nameDriver, String phoneNumberDriver) {
        CarDriverDb.getInstance().updateWithoutDriverId(car, nameDriver, phoneNumberDriver);
    }

    public Car findCarBySerialNumber(int serialNumber) {
        return CarDriverDb.getInstance().findBySerialNumber(serialNumber);
    }

    public MyList<Car> findAllCars() {
        return CarDriverDb.getInstance().findAllCar();
    }
}
