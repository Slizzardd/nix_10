package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.CarDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Car;
import ua.com.alevel.service.CarService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    private final CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public void create(Car entity) {
        carDao.create(entity);
    }

    @Override
    public void update(Car entity) {
        carDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        carDao.delete(id);
    }

    @Override
    public Car findById(Long id) {
        return carDao.findById(id);
    }

    @Override
    public DataTableResponse<Car> findAll(DataTableRequest request) {
        DataTableResponse<Car> dataTableResponse = carDao.findAll(request);
        long count = carDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findByDriverId(Long id) {
        return carDao.findByDriverId(id);
    }
}
