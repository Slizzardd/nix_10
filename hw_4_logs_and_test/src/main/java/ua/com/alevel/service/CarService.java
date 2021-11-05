package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.List.MyList;
import ua.com.alevel.entity.Car;
import ua.com.alevel.dao.CarDao;

public class CarService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private final CarDao CAR_DAO = new CarDao();

    public void create(Car car) {
        LOGGER_INFO.info("create new car: " + car.getManufacture());
        CAR_DAO.create(car);
        LOGGER_INFO.info("finish creating car : " +
                "manufacture: '" + car.getManufacture() + '\'' +
                ", brand: '" + car.getBrand() + '\'' +
                ",yearOfIssue:'" + car.getYearOfIssue() + '\'' +
                '}');
    }

    public void update(Car car) {
        LOGGER_INFO.info("update car: " + car.getManufacture());
        CAR_DAO.update(car);
        LOGGER_INFO.info("finish update car : " +
                "manufacture: '" + car.getManufacture() + '\'' +
                ", brand: '" + car.getBrand() + '\'' +
                ",yearOfIssue:'" + car.getYearOfIssue() + '\'' +
                '}');
    }

    public void updateWithoutDriverId() {
        CAR_DAO.updateWithoutDriverId();
    }

    public void delete(int serialNumber) {
        LOGGER_WARN.warn("remove car by serialNumber" + serialNumber);
        CAR_DAO.delete(serialNumber);
    }

    public Car findCarBySerialNumber(int serialNumber) {
        return CAR_DAO.findCarBySerialNumber(serialNumber);
    }

    public MyList<Car> findAllCars() {
        return CAR_DAO.findAllCars();
    }
}
