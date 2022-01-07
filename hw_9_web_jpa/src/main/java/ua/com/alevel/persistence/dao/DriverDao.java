package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Driver;

import java.util.Map;

public interface DriverDao extends BaseDao<Driver> {

    void create(Driver entity);

    Map<Long, String> findAllByCarId(Long carId);
}
