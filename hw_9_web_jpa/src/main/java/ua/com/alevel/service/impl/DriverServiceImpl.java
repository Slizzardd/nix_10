package ua.com.alevel.service.impl;

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
        if (driverDao.existById(entity.getId())) {
            driverDao.update(entity);
        } else {
            throw new EntityNotFoundException("Driver not found:(");
        }
    }

    @Override
    public void delete(Long id) {
        if (driverDao.existById(id)) {
            driverDao.delete(id);
        } else {
            throw new EntityNotFoundException("Driver not found:(");
        }
    }

    @Override
    public Driver findById(Long id) {
        Driver driverFindById = driverDao.findById(id);
        if (driverFindById == null) {
            throw new EntityNotFoundException("Driver not found:(");
        } else {
            return driverFindById;
        }
    }

    @Override
    public DataTableResponse<Driver> findAll(DataTableRequest request) {
        DataTableResponse<Driver> dataTableResponse = new DataTableResponse<>();
        try{
            dataTableResponse = driverDao.findAll(request);
        }catch (Exception e){
            e.printStackTrace();
        }
        long count = driverDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> finAllByCarId(Long carId) {
        return driverDao.findAllByCarId(carId);
    }
}
