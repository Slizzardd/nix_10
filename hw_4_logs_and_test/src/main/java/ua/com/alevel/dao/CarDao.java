package ua.com.alevel.dao;

import ua.com.alevel.entity.Car;
import ua.com.alevel.db.CarDriverDb;
import ua.com.alevel.List.MyList;

public class CarDao {

    public void create(Car car) {
        CarDriverDb.getInstance().create(car);
    }

    public void update(Car car) {
        CarDriverDb.getInstance().update(car);
    }

    public void delete(int serialNumber) {
        CarDriverDb.getInstance().delete(serialNumber, CarDriverDb.Entity.CAR);
    }

    public Car findCarBySerialNumber(int serialNumber) {
        return CarDriverDb.getInstance().findBySerialNumber(serialNumber);
    }

    public MyList<Car> findAllCars() {
        return CarDriverDb.getInstance().findAllCar();
    }
}
