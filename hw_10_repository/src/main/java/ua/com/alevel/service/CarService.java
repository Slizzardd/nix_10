package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Car;

import java.util.Map;

public interface CarService extends BaseService<Car> {

    void help();

    void create(Car entity, Long driverId);

    Map<Long, String> findDriversByCarId(Long carId);
}
