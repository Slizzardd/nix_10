package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Driver;

import java.util.Map;

public interface DriverService extends BaseService<Driver> {

    void create(Driver entity);

    Map<Long, String> finAllByCarId(Long carId);
}
