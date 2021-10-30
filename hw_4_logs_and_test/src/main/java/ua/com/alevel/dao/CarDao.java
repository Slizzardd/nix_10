package ua.com.alevel.dao;

import ua.com.alevel.entity.Car;
import ua.com.alevel.db.CarDb;
public class CarDao {
    public void createCar(Car car) {
        CarDb.getInstance().create(car);
    }

    public void updateCar(Car driver) {
        CarDb.getInstance().update(driver);
    }

    public void deleteCar(String serialNumber) {
        CarDb.getInstance().delete(serialNumber);
    }

    public Car findBySerialNumberCar(String serialNumber) {
        return CarDb.getInstance().findBySerialNumber(serialNumber);
    }

    public String userIdByNameAndPhoneNumber(){return CarDb.getInstance().userIdByNameAndPhoneNumber();}

    public Car[] findAllCar() {
        return CarDb.getInstance().findAllCar();
    }

    public void deleteByIdOwnerCar(String idOwner){
        CarDb.getInstance().deleteByIdOwner(idOwner);
    }
}
