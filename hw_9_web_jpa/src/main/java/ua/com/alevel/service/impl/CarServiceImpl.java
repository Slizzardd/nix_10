package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.CarDao;
import ua.com.alevel.persistence.dao.DriverDao;
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
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final CarDao carDao;
    private final DriverDao driverDao;

    public CarServiceImpl(CarDao carDao, DriverDao driverDao) {
        this.carDao = carDao;
        this.driverDao = driverDao;
    }

    @Override
    public void create(Car entity, Long driverId) throws NullPointerException {
        LOGGER_INFO.info("CREATING CAR(CAR_NAME): " + entity.getCarName());
        if (driverId == null || driverId == 0L) {
            carDao.create(entity);
        } else {
            driverDao.createRelation(driverId, entity);
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
        if (carDao.existById(entity.getId())) {
            LOGGER_INFO.info("done!");
            carDao.update(entity);
        } else {
            LOGGER_INFO.error("car not found!!!");
            throw new EntityNotFoundException("Car not found:(");
        }
    }

    @Override
    public void delete(Long id) {
        LOGGER_INFO.info("DELETE CAR(CAR_ID): " + id);
        if (carDao.existById(id)) {
            LOGGER_INFO.info("done");
            carDao.delete(id);
        } else {
            LOGGER_INFO.error("car not found!!!");
            throw new EntityNotFoundException("Car not found:(");
        }
    }

    @Override
    public Car findById(Long id) {
        LOGGER_INFO.info("FIND_BY_ID CAR(CAR_ID): " + id);
        Car carFindById = carDao.findById(id);
        if (carFindById == null) {
            LOGGER_INFO.error("car not found!!!");
            throw new EntityNotFoundException("Car not found:(");
        } else {
            LOGGER_INFO.info("done");
            return carFindById;
        }
    }

    @Override
    public DataTableResponse<Car> findAll(DataTableRequest request) {
        LOGGER_INFO.info("FIND ALL CAR");
        DataTableResponse<Car> dataTableResponse = null;
        try {
            dataTableResponse = carDao.findAll(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long count = carDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findDriversByCarId(Long carId) {
        LOGGER_INFO.info("FIND DRIVERS BY CAR(CAR_ID): " + carId);
        return carDao.findDriversByCarId(carId);
    }
}
