package ua.com.alevel.dao;

import ua.com.alevel.entity.Driver;
import ua.com.alevel.db.DriverDb;
public class DriverDao {
    public void createDriver(Driver driver) {
        DriverDb.getInstance().create(driver);
    }

    public void updateDriver(Driver driver) {
        DriverDb.getInstance().update(driver);
    }

    public void deleteDriver(String id) {
        DriverDb.getInstance().delete(id);
    }

    public Driver findByIdDriver(String id) {
        return DriverDb.getInstance().findById(id);
    }

    public Driver[] findAllDriver() {
        return DriverDb.getInstance().findAll();
    }
}
