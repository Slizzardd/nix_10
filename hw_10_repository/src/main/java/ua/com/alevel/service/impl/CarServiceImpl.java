package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Car;
import ua.com.alevel.persistence.entity.Driver;
import ua.com.alevel.persistence.repository.repository.CarRepository;
import ua.com.alevel.persistence.repository.repository.DriverRepository;
import ua.com.alevel.service.CarService;
import ua.com.alevel.service.DriverService;

import java.util.*;

@Service
public class CarServiceImpl implements CarService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final CrudRepositoryHelper<Car, CarRepository> crudRepositoryHelper;
    private final CarRepository carRepository;
    private final DriverRepository driverRepository;
    private final DriverService driverService;

    public CarServiceImpl(
            CrudRepositoryHelper<Car, CarRepository> crudRepositoryHelper, CarRepository carRepository,
            DriverRepository driverRepository, DriverService driverService) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
        this.driverService = driverService;
    }


    @Override
    public void create(Car entity, Long driverId) {
        LOGGER_INFO.info("CREATING CAR(CAR_NAME): " + entity.getCarName());
        if(driverId == 0){
            crudRepositoryHelper.create(carRepository, entity);
        }else{
            Driver driver = driverRepository.findById(driverId).get();
            driver.addCar(entity);
            driverService.update(driver);
        }
        LOGGER_INFO.info("Finish creating car:" +
                "\ndriverId: " + driverId +
                "\ncar name: " + entity.getCarName() +
                "\ncar number: " + entity.getCarNumber() +
                "\ncar color: " + entity.getColor() +
                "\ncar yearsOfIssue: " + entity.getYearsOfIssue() +
                "\ncar engineOfCapacity: " + entity.getEngineCapacity() +
                "\ncar imageURL: " + entity.getImageUrl());
    }

    @Override
    public void update(Car entity) {
        LOGGER_INFO.info("UPDATE CAR: " + entity.getCarName());
        crudRepositoryHelper.update(carRepository, entity);
    }

    @Override
    public void delete(Long id) {
        LOGGER_INFO.info("DELETE CAR(CAR_ID): " + id);
        carRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Car> findById(Long id) {
        LOGGER_INFO.info("FIND_BY_ID CAR(CAR_ID): " + id);
        return crudRepositoryHelper.findById(carRepository, id);
    }

    @Override
    public DataTableResponse<Car> findAll(DataTableRequest request) {
        LOGGER_INFO.info("FIND ALL CAR");
        return crudRepositoryHelper.findAll(carRepository, request);
    }

    @Override
    public void help() {
        System.out.println("CarServiceImpl.help");
        crudRepositoryHelper.help();
        crudRepositoryHelper.help();
        crudRepositoryHelper.help();
        crudRepositoryHelper.help();
    }

    @Override
    public Map<Long, String> findDriversByCarId(Long carId) {
        LOGGER_INFO.info("FIND DRIVERS BY CAR(CAR_ID): " + carId);
        List<Driver> driverList = driverRepository.findDriversByCarId(carId);
        Map<Long, String> drivers = new HashMap<>();
        for (Driver driver : driverList) {
            drivers.put(driver.getId(), driver.getFirstName() + " " + driver.getLastName());
        }
        return drivers;
    }

    private List<Driver> findAllDriversByCarId(Long carId) {
        LOGGER_INFO.info("FIND DRIVERS BY CAR(CAR_ID): " + carId);
        return driverRepository.findDriversByCarId(carId);
    }
}
