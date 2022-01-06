package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.DriverDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Driver;
import ua.com.alevel.service.DriverService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.Map;

@Service
public class DriverServiceImpl implements DriverService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private final DriverDao driverDao;

    public DriverServiceImpl(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @Override
    public void create(Driver entity) {
        LOGGER_INFO.info("Create new driver: " + entity.getFirstName() + " " + entity.getLastName());
        driverDao.create(entity);
        LOGGER_INFO.info("Finish creating car:" +
                "\nDriver firstName: " + entity.getFirstName() +
                "\nDriver lastName: " + entity.getLastName() +
                "\nDriver notes: " + entity.getNotes() +
                "\nDriver balance: " + entity.getBalance() +
                "\nDriver imageURL: " + entity.getImageUrl());
    }

    @Override
    public void update(Driver entity) {
        LOGGER_INFO.info("Update driver: " + entity.getFirstName() + " " + entity.getLastName());
        if (driverDao.existById(entity.getId())) {
            LOGGER_INFO.info("DONE");
            driverDao.update(entity);
        } else {
            LOGGER_INFO.info("DRIVER NOT FOUND!!!");
            throw new EntityNotFoundException("Driver not found:(");
        }
    }

    @Override
    public void delete(Long id) {
        LOGGER_INFO.info("delete driverId: " + id);
        if (driverDao.existById(id)) {
            LOGGER_INFO.info("DONE");
            driverDao.delete(id);
        } else {
            LOGGER_INFO.info("DRIVER NOT FOUND!!!");
            throw new EntityNotFoundException("Driver not found:(");
        }
    }

    @Override
    public Driver findById(Long id) {
        LOGGER_INFO.info("findById driverId: " + id);
        Driver driverFindById = driverDao.findById(id);
        if (driverFindById == null) {
            LOGGER_INFO.info("DRIVER NOT FOUND!!!");
            throw new EntityNotFoundException("Driver not found:(");
        } else {
            LOGGER_INFO.info("DONE");
            return driverFindById;
        }
    }

    @Override
    public DataTableResponse<Driver> findAll(DataTableRequest request) {
        LOGGER_INFO.info("find all driver");
        DataTableResponse<Driver> dataTableResponse = driverDao.findAll(request);
        long count = driverDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> finAllByCarId(Long carId) {
        LOGGER_INFO.info("find driver by car id");
        return driverDao.findAllByCarId(carId);
    }
}
