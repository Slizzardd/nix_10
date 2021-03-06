package ua.com.alevel.task1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.List.MyList;
import ua.com.alevel.persistence.entity.Car;
import ua.com.alevel.dao.CarDao;

public class CarService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private final CarDao CAR_DAO = new CarDao();

    public void create(Car car, String nameDriver, String phoneNumberDriver) {
        LOGGER_INFO.info("create new car: " + car.getManufacture());
        CAR_DAO.create(car, nameDriver, phoneNumberDriver);
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

    public void updateWithoutDriverId(Car car, String nameDriver, String phoneNumberDriver) {
        CAR_DAO.updateWithoutDriverId(car, nameDriver, phoneNumberDriver);
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
