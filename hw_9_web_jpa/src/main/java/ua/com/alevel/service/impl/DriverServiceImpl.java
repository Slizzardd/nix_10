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
import ua.com.alevel.persistence.entity.Driver;
import ua.com.alevel.service.DriverService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class DriverServiceImpl implements DriverService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final DriverDao driverDao;
    private final CarDao carDao;

    public DriverServiceImpl(DriverDao driverDao, CarDao carDao) {
        this.driverDao = driverDao;
        this.carDao = carDao;
    }

    @Override
    public void create(Driver entity) {
        LOGGER_INFO.info("CREATE NEW DRIVER: " + entity.getFirstName() + " " + entity.getLastName());
        driverDao.create(entity);
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
        if (driverDao.existById(entity.getId())) {
            LOGGER_INFO.info("done");
            driverDao.update(entity);
        } else {
            LOGGER_INFO.error("driver not found at driver_id: " + entity.getId());
            throw new EntityNotFoundException("Driver not found:(");
        }
    }

    @Override
    public void delete(Long id) {
        LOGGER_INFO.info("DELETE DRIVER AND RELATION(DRIVER_ID): " + id);
        Set<Car> cars = findById(id).getCars();
        for (Car car : cars) {
            if (car.getDrivers().size() <= 1) {
                LOGGER_INFO.info("DELETE CAR(CAR_ID): " + id);
                carDao.delete(car.getId());
            }
        }
        LOGGER_INFO.info("done delete!");
        driverDao.delete(id);
    }

    @Override
    public Driver findById(Long id) {
        LOGGER_INFO.info("FIND_BY_DRIVER_ID: " + id);
        Driver driverFindById = driverDao.findById(id);
        if (driverFindById == null) {
            LOGGER_INFO.error("driver not found!!!");
            throw new EntityNotFoundException("Driver not found:(");
        } else {
            LOGGER_INFO.info("done find");
            return driverFindById;
        }
    }

    @Override
    public DataTableResponse<Driver> findAll(DataTableRequest request) {
        LOGGER_INFO.info("FIND ALL DRIVERS");
        DataTableResponse<Driver> dataTableResponse = null;
        try {
            dataTableResponse = driverDao.findAll(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long count = driverDao.count();
        assert dataTableResponse != null;
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findCarsByDriverId(Long driverId) {
        LOGGER_INFO.info("FIND MAP<ID, NAME>CARS BY DRIVER(DRIVER_ID): " + driverId);
        return driverDao.findCarsByDriverId(driverId);
    }

    @Override
    public List<Car> findAllCarsByDriverId(Long driverId) {
        LOGGER_INFO.info("FIND LIST<CAR>CARS BY DRIVER(DRIVER_ID): " + driverId);
        return driverDao.findAllCarsByDriverId(driverId);
    }

    @Override
    public void createRelation(Long driverId, Long carId) throws NullPointerException {
        LOGGER_INFO.info("CREATE RELATION DRIVER_ID: " + driverId + " CAR_ID: " + carId);
        driverDao.createRelation(driverId, carDao.findById(carId));
    }
}
