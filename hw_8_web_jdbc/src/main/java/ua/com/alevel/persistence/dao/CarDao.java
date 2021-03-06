package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Car;

import java.util.List;
import java.util.Map;

public interface CarDao extends BaseDao<Car> {

    void create(Car entity, Long driverId);

    Map<Long, String> findByDriverId(Long id);

    List<Car> findAllDriverId(Long id);

    List<Long> findAllCarByDriverId(Long id);
}
