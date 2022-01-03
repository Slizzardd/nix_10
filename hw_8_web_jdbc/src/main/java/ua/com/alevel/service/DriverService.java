package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Driver;

import java.util.Map;

public interface DriverService extends BaseService<Driver> {

    Map<Long, String> finAllByCarId(Long carId);
}
