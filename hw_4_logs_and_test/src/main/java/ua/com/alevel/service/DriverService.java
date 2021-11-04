package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.List.MyList;
import ua.com.alevel.entity.Driver;
import ua.com.alevel.dao.DriverDao;

public class DriverService {
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private final DriverDao DRIVER_DAO = new DriverDao();

    public void create(Driver driver) {
        LOGGER_INFO.info("create new driver: " + driver.getName());
        DRIVER_DAO.create(driver);
        LOGGER_INFO.info("finish creating driver : " +
                "name: '" + driver.getName() + '\'' +
                ", phone Number: '" + driver.getPhoneNumber() + '\'' +
                ",id:'" + driver.getId() + '\'' +
                '}');
    }

    public void update(Driver driver) {
        LOGGER_INFO.info("update driver: " + driver.getName());
        DRIVER_DAO.update(driver);
        LOGGER_INFO.info("finish update driver : " +
                "name: '" + driver.getName() + '\'' +
                ", phone Number: '" + driver.getPhoneNumber() + '\'' +
                ",id:'" + driver.getId() + '\'' +
                '}');
    }

    public void delete(int id) {
        LOGGER_INFO.info("delete driver in id: " + id);
        DRIVER_DAO.delete(id);
    }

    public Driver findDriverById(int id) {
        return DRIVER_DAO.findDriverById(id);
    }

    public MyList<Driver> findAllDrivers() {
        return DRIVER_DAO.findAllDrivers();
    }
}
