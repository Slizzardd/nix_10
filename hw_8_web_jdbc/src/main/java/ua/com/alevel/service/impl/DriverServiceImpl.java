package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.DriverDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Driver;
import ua.com.alevel.service.DriverService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.Map;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverDao driverDao;

    public DriverServiceImpl(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @Override
    public void create(Driver entity) {
        driverDao.create(entity);
    }

    @Override
    public void update(Driver entity) {
        driverDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        driverDao.delete(id);
    }

    @Override
    public Driver findById(Long id) {
        return driverDao.findById(id);
    }

    @Override
    public DataTableResponse<Driver> findAll(DataTableRequest request) {
        DataTableResponse<Driver> dataTableResponse = driverDao.findAll(request);
        long count = driverDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> finAllByCarId(Long carId) {
        return driverDao.findAllByCarId(carId);
    }
}
