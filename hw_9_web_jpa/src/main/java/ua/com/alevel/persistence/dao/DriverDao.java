package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Car;
import ua.com.alevel.persistence.entity.Driver;

import java.util.List;
import java.util.Map;

public interface DriverDao extends BaseDao<Driver> {

    Map<Long, String> findCarsByDriverId(Long driverId);

    List<Car> findAllCarsByDriverId(Long driverId);

    void createRelation(Long driverId, Car car);
}
