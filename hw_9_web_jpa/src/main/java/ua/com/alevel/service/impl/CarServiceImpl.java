package ua.com.alevel.service.impl;

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
        if (carDao.existById(entity.getId())) {
            carDao.update(entity);
        } else {
            throw new EntityNotFoundException("Car not found:(");
        }
    }

    @Override
    public void delete(Long id) {
        if (carDao.existById(id)) {
            carDao.delete(id);
        } else {
            throw new EntityNotFoundException("Car not found:(");
        }
    }

    @Override
    public Car findById(Long id) {
        Car carFindById = carDao.findById(id);
        if (carFindById == null) {
            throw new EntityNotFoundException("Car not found:(");
        } else {
            return carFindById;
        }
    }

    @Override
    public DataTableResponse<Car> findAll(DataTableRequest request) {
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
    public Map<Long, String> findByDriverId(Long id) {
        return carDao.findByDriverId(id);
    }
}
