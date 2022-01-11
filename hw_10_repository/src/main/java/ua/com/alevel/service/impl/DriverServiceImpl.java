package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Car;
import ua.com.alevel.persistence.entity.Driver;
import ua.com.alevel.persistence.repository.repository.CarRepository;
import ua.com.alevel.persistence.repository.repository.DriverRepository;
import ua.com.alevel.service.DriverService;

import java.util.*;

@Service
public class DriverServiceImpl implements DriverService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private final CrudRepositoryHelper<Driver, DriverRepository> crudRepositoryHelper;
    private final DriverRepository driverRepository;
    private final CarRepository carRepository;

    public DriverServiceImpl(
            CrudRepositoryHelper<Driver, DriverRepository> crudRepositoryHelper,
            CrudRepositoryHelper<Car, CarRepository> crudRepositoryHelperCar,
            DriverRepository driverRepository, CarRepository carRepository) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void create(Driver entity) {
        LOGGER_INFO.info("CREATE NEW DRIVER: " + entity.getFirstName() + " " + entity.getLastName());
        crudRepositoryHelper.create(driverRepository, entity);
        LOGGER_INFO.info("finish creating car:" +
                "\ndriver firstName: " + entity.getFirstName() +
                "\ndriver lastName: " + entity.getLastName() +
                "\ndriver notes: " + entity.getNotes() +
                "\ndriver balance: " + entity.getBalance() +
                "\ndriver imageURL: " + entity.getImageUrl());
    }

    @Override
    public void update(Driver entity) {
        LOGGER_INFO.info("UPDATE DRIVER: " + entity.getFirstName() + " " + entity.getLastName());
        crudRepositoryHelper.update(driverRepository, entity);
    }

    @Override
    public void delete(Long id) {
        LOGGER_INFO.info("DELETE DRIVER AND RELATION(DRIVER_ID): " + id);
        Set<Car> cars = findById(id).get().getCars();
        for (Car car : cars) {
            if (car.getDrivers().size() <= 1) {
                LOGGER_INFO.info("DELETE CAR(CAR_ID): " + id);
                carRepository.delete(car.getId());
            }
        }
        driverRepository.delete(id);
    }

    @Override
    public Optional<Driver> findById(Long id) {
        LOGGER_INFO.info("FIND_BY_DRIVER_ID: " + id);
        return crudRepositoryHelper.findById(driverRepository, id);
    }

    @Override
    @Transactional()
    public DataTableResponse<Driver> findAll(DataTableRequest request) {
        LOGGER_INFO.info("FIND ALL DRIVERS");
        DataTableResponse<Driver> all = crudRepositoryHelper.findAll(driverRepository, request);
        Map<Object, Object> otherParamMap = new HashMap<>();
        for (Driver driver : all.getItems()) {
            otherParamMap.put(driver.getId(), countNumOfCars(driver.getId()));
        }
        all.setOtherParamMap(otherParamMap);
        return all;
    }

    private int countNumOfCars(Long id) {
        return findById(id).get().getCars().size();
    }

    @Override
    public Map<Long, String> findCarsByDriverId(Long driverId) {
        LOGGER_INFO.info("FIND MAP<ID, NAME>CARS BY DRIVER(DRIVER_ID): " + driverId);
        List<Car> carList = carRepository.findCarsByDriverId(driverId);
        Map<Long, String> drivers = new HashMap<>();
        for (Car car : carList) {
            drivers.put(car.getId(), car.getCarName());
        }
        return drivers;
    }

    @Override
    public List<Car> findAllCarsByDriverId(Long driverId) {
        LOGGER_INFO.info("FIND LIST<CAR>CARS BY DRIVER(DRIVER_ID): " + driverId);
        return carRepository.findCarsByDriverId(driverId);
    }

    @Override
    public void createRelation(Long driverId, Long carId) {
        LOGGER_INFO.info("CREATE RELATION DRIVER_ID: " + driverId + " CAR_ID: " + carId);
        Driver driver = findById(driverId).get();
        driver.addCar(carRepository.getById(carId));
        update(driver);
    }
}
