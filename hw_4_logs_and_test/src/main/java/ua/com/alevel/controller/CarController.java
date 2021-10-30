package ua.com.alevel.controller;


import ua.com.alevel.entity.Car;
import ua.com.alevel.service.CarService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CarController {

    private static CarController instance;
    private final CarService carService = new CarService();
    public static CarController getInstance() {
        if (instance == null) {
            instance = new CarController();
        }
        return instance;
    }

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите действие относитель владельца: ");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("Проблема: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("Если вы хотите создать новую машину, нажмите 1");
        System.out.println("Если вы хотите обновить машину, нажмите 2");
        System.out.println("Если вы хотите удалить маишну, нажмите 3");
        System.out.println("Если вы хотите найти машину по серийному номеру авто, нажмите 4");
        System.out.println("Если вы хотите получить список всех машин, нажмите  5");
        System.out.println();
    }

    public void crud(String position, BufferedReader reader) throws IOException {
        System.out.println("Выберите действие: ");
        runNavigation();
        String line = reader.readLine();
        switch (line) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findBySerialNumber(reader);
            case "5" -> findAll(reader);
        }
    }

    private void create(BufferedReader reader) {
        System.out.println("Создание  машины: ");
        try {
            System.out.println("Пожалуйста, введите бренд изготовителя: ");
            String manufacture = reader.readLine();
            System.out.println("Пожалуйста, введите марку авто: ");
            String brand = reader.readLine();
            System.out.println("Пожалуйста, введите год выпуска авто: ");
            String line = reader.readLine();
            int age = Integer.parseInt(line);

            Car car = new Car();
            car.setAge(age);
            car.setBrand(brand);
            car.setManufacturer(manufacture);
            carService.createCar(car);
        } catch (IOException e) {
            System.out.println("проблема: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("Обновление машину");
        try {
            System.out.println("Введите серийный номер(VIN) для поиска: ");
            String serialNumber = reader.readLine();
            System.out.println("Пожалуйста, введите нового производителя: ");
            String manufacture = reader.readLine();
            System.out.println("Пожалуйста, введите новую марку: ");
            String brand = reader.readLine();
            System.out.println("Пожалуста, введите новый год выпуска: ");
            String ageString = reader.readLine();
            int age = Integer.parseInt(ageString);
            Car car = new Car();
            car.setSerialNumber(serialNumber);
            car.setManufacturer(manufacture);
            car.setBrand(brand);
            car.setAge(age);
            carService.updateCar(car);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("Удаление пользователя");
        try {
            System.out.println("Пожалуйста, введите серийный номер(VIM): ");
            String serialNumber = reader.readLine();
            carService.deleteCar(serialNumber);
        } catch (IOException e) {
            System.out.println("Проблема: = " + e.getMessage());
        }
    }

    private void findBySerialNumber(BufferedReader reader) {
        System.out.println("Поиск по серийному номеру");
        try {
            System.out.println("Пожалуйста, введите серийный номер(VIM): ");
            String serialNumber = reader.readLine();
            Car car = carService.findBySerialNumberCar(serialNumber);
            System.out.println("Пользователь = " + car);
        } catch (IOException e) {
            System.out.println("Проблема: = " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.println("Поиск всех пользователей");
        Car[] cars = carService.findAllCar();
        if(cars !=null && cars.length !=0 ){
            for(Car car : cars){
                System.out.println("Пользователь: " + car);
            }
        }else{
            System.out.println("Никого нет");
        }
    }
}
