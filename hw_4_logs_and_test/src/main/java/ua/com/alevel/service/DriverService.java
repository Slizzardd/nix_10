package ua.com.alevel.service;

import ua.com.alevel.dao.DriverDao;
import ua.com.alevel.db.DriverDb;
import ua.com.alevel.entity.Driver;

public class DriverService {
    private final DriverDao driverDao = new DriverDao();

    public void createDriver(Driver driver){
        driverDao.createDriver(driver);
    }

    public void updateDriver(Driver driver){
        driverDao.updateDriver(driver);
    }

    public void deleteDriver(String id) {
        driverDao.deleteDriver(id);
    }

    public Driver findByIdDriver(String id) {
        return driverDao.findByIdDriver(id);
    }

    public Driver[] findAllDriver() {
        return driverDao.findAllDriver();
    }
}
