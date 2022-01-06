package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.CarDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Car;
import ua.com.alevel.service.CarService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private final CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public void create(Car entity, Long driverId) {
        LOGGER_INFO.info("Create new car: " + entity.getCarName());
        carDao.create(entity, driverId);
        LOGGER_INFO.info("Finish creating car:" +
                "\nDriverId: " + driverId +
                "\nCar name: " + entity.getCarName() +
                "\nCar number: " + entity.getCarNumber() +
                "\nCar color: " + entity.getColor() +
                "\nCar yearsOfIssue: " + entity.getYearsOfIssue() +
                "\nCar engineOfCapacity: " + entity.getEngineCapacity() +
                "\nCar imageURL: " + entity.getImageUrl());

    }

    @Override
    public void update(Car entity) {
        LOGGER_INFO.info("Update car: " + entity.getCarName());
        if (carDao.existById(entity.getId())) {
            LOGGER_INFO.info("DONE");
            carDao.update(entity);
        }else{
            LOGGER_INFO.info("CAR NOT FOUND!!!");
            throw new EntityNotFoundException("Car not found:(");
        }
    }

    @Override
    public void delete(Long id) {
        LOGGER_INFO.info("delete carId: " + id);
        if (carDao.existById(id)) {
            LOGGER_INFO.info("DONE");
            carDao.delete(id);
        }else{
            LOGGER_INFO.info("CAR NOT FOUND!!!");
            throw new EntityNotFoundException("Car not found:(");
        }
    }

    @Override
    public Car findById(Long id) {
        LOGGER_INFO.info("findById carId: " + id);
        Car carFindById = carDao.findById(id);
        if (carFindById == null) {
            LOGGER_INFO.info("CAR NOT FOUND!!!");
            throw new EntityNotFoundException("Car not found:(");
        }else{
            LOGGER_INFO.info("DONE");
            return carFindById;
        }
    }

    @Override
    public DataTableResponse<Car> findAll(DataTableRequest request) {
        LOGGER_INFO.info("find all car");
        DataTableResponse<Car> dataTableResponse = carDao.findAll(request);
        long count = carDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findByDriverId(Long id) {
        LOGGER_INFO.info("find car by driver id");
        return carDao.findByDriverId(id);
    }
}
