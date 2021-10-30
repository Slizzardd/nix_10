package ua.com.alevel.service;

import ua.com.alevel.dao.CarDao;
import ua.com.alevel.entity.Car;

public class CarService {
    private CarDao carDao = new CarDao();

    public void createCar(Car car) {
        carDao.createCar(car);
    }

    public void updateCar(Car driver) {
        carDao.updateCar(driver);
    }

    public void deleteCar(String serialNumber) {
        carDao.deleteCar(serialNumber);
    }

    public Car findBySerialNumberCar(String serialNumber) {
        return carDao.findBySerialNumberCar(serialNumber);
    }

    public Car[] findAllCar() {
        return carDao.findAllCar();
    }

    public void deleteByIdOwnerCar(String idOwner){
        carDao.deleteByIdOwnerCar(idOwner);
    }

    public String userIdByNameAndPhoneNumber(){return carDao.userIdByNameAndPhoneNumber();}
}
