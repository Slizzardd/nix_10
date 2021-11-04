package ua.com.alevel.dao;

import ua.com.alevel.List.MyList;
import ua.com.alevel.db.CarDriverDb;
import ua.com.alevel.entity.Driver;

public class DriverDao {

    public void create(Driver driver) {
        CarDriverDb.getInstance().create(driver);
    }

    public void update(Driver driver) {
        CarDriverDb.getInstance().update(driver);
    }

    public void delete(int serialNumber) {
        CarDriverDb.getInstance().delete(serialNumber, CarDriverDb.Entity.DRIVER);
    }

    public Driver findDriverById(int id) {
        return CarDriverDb.getInstance().findById(id);
    }

    public MyList<Driver> findAllDrivers() {
        return CarDriverDb.getInstance().findAllDrivers();
    }
}
