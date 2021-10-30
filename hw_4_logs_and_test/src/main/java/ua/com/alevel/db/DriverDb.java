package ua.com.alevel.db;

import ua.com.alevel.entity.Driver;

import java.util.Scanner;
import java.util.UUID;

public class DriverDb{

    public Driver[] drivers = new Driver[5];
    private static DriverDb instance;
    private int userSize = 0;


    public static DriverDb getInstance() {
        if (instance == null) {
            instance = new DriverDb();
        }
        return instance;
    }

    public String userIdByNameAndPhoneNumber(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя владельца: ");
        String name = scanner.nextLine();
        System.out.println("Введите номер телефона владельца: ");
        String number = scanner.nextLine();
        for(Driver driver : drivers){
            if(name.equals(driver.getName()) && number.equals(driver.getNumber())){
                return driver.getId();
            }
        }
        throw new RuntimeException("Такого пользователя не существует:(");
    }
    public void create(Driver driver) {
        driver.setId(generateId());
        copy(driver);
        userSize++;
    }

    private void copy(Driver driver) {
        drivers[userSize] = driver;
        if (userSize == drivers.length - 1) {
            drivers[userSize] = driver;
            Driver[] tempUser = new Driver[drivers.length + 5];
            System.arraycopy(drivers, 0, tempUser, 0, drivers.length);
            drivers = tempUser;
        }
    }

    private String generateId(){
        String id = UUID.randomUUID().toString();
        for(Driver driver : drivers){
            if(driver != null && driver.getId().equals(id)){
                return generateId();
            }
        }
        return id;
    }

    public void delete(String id) {
        for (int i = 0; i < drivers.length; i++) {
            if (id.equals(drivers[i].getId())) {
                CarDb.getInstance().deleteByIdOwner(id);
                for (int p = i; p < drivers.length; p++) {
                    if (p != drivers.length - 1) {
                        drivers[p] = drivers[p + 1];
                    } else {
                        drivers[p] = null;
                    }
                }
                break;
            }
        }
    }

    public void update(Driver driver){
        Driver current = findById(driver.getId());
        current.setName(driver.getName());
        current.setAge(driver.getAge());
        current.setNumber(driver.getNumber());
        current.setEmail(driver.getEmail());
    }

    public Driver findById(String id) {
        for (Driver driver : drivers) {
            if (driver.getId().equals(id)) {
                return driver;
            }else{
                return null;
            }
        }
        throw new RuntimeException("НЕТ ТАКОГО ID!!!");
    }

    public Driver[] findAll() {
        return drivers;
    }
}
