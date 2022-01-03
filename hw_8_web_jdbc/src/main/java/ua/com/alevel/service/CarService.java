package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Car;

import java.util.Map;

public interface CarService extends BaseService<Car>{

    Map<Long, String> findByDriverId(Long id);
}
