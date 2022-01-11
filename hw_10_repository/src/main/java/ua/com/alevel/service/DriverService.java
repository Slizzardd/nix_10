package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Car;
import ua.com.alevel.persistence.entity.Driver;

import java.util.List;
import java.util.Map;

public interface DriverService extends BaseService<Driver> {

    void create(Driver driver);

    Map<Long, String> findCarsByDriverId(Long driverId);

    List<Car> findAllCarsByDriverId(Long driverId);

    void createRelation(Long driverId, Long carId);
}
