package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Driver;

import java.util.Map;

public interface DriverDao extends BaseDao<Driver> {
    Map<Long, String> findAllByCarId(Long carId);
}
