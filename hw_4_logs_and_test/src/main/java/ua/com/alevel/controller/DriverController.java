package ua.com.alevel.controller;

import ua.com.alevel.entity.Driver;
import ua.com.alevel.service.DriverService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DriverController {

    private static DriverController instance;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private final DriverService driverService = new DriverService();

    public static DriverController getInstance() {
        if (instance == null) {
            instance = new DriverController();
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
        System.out.println("Если вы хотите создать нового посетителя, нажмите 1");
        System.out.println("Если вы хотите обновить посетителя, нажмите 2");
        System.out.println("Если вы хотите удалить посетителя, нажмите 3");
        System.out.println("Если вы хотите найти посетителя по серийному номеру авто, нажмите 4");
        System.out.println("Если вы хотите получить список всех посетителей, нажмите  5");
        System.out.println();
    }

    public void crud(String line, BufferedReader reader) throws IOException {
        System.out.println("Выберите действие: ");
        runNavigation();
        String position = reader.readLine();
        switch (position) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findAll(reader);
        }
    }

    private void create(BufferedReader reader) {
        System.out.println("Создание владельца машины: ");
        try {
            System.out.println("Пожалуйста, введите имя: ");
            String name = reader.readLine();
            System.out.println("Пожалуйста, введите ваш год рождения: ");
            String ageString = reader.readLine();
            int age = Integer.parseInt(ageString);
            System.out.println("Пожалуйста, введите email: ");
            String email = reader.readLine();
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            boolean emailRegex =  matcher.find();
            System.out.println("emailRegex = " + emailRegex);
            System.out.println("Пожалуйста, введите номер мобильного телефона: ");
            String phoneNumber = reader.readLine();
            Driver driver = new Driver();
            driver.setAge(age);
            driver.setName(name);
            driver.setEmail(email);
            driver.setNumber(phoneNumber);
            driverService.createDriver(driver);
        } catch (IOException e) {
            System.out.println("проблема: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("Обновление пользователя");
        try {
            System.out.println("Введите Id пользователя для поиска: ");
            String id = reader.readLine();
            System.out.println("Пожалуйста, введите новое имя: ");
            String name = reader.readLine();
            System.out.println("Пожалуйста, введите новый год рождения: ");
            String ageString = reader.readLine();
            int age = Integer.parseInt(ageString);
            System.out.println("Пожалуйста, введите новый email: ");
            String email = reader.readLine();
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            boolean emailRegex =  matcher.find();
            System.out.println("emailRegex = " + emailRegex);
            System.out.println("Пожалуйста, введите новый номер мобильного телефона: ");
            String phoneNumber = reader.readLine();
            Driver driver = new Driver();
            driver.setId(id);
            driver.setName(name);
            driver.setEmail(email);
            driver.setNumber(phoneNumber);
            driverService.updateDriver(driver);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("Удаление пользователя");
        try {
            System.out.println("Пожалуйста, введите id пользователя: ");
            String id = reader.readLine();
            driverService.deleteDriver(id);
        } catch (IOException e) {
            System.out.println("Проблема: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("Поиск по id");
        try {
            System.out.println("Пожалуйста, введите id: ");
            String id = reader.readLine();
            Driver user = driverService.findByIdDriver(id);
            System.out.println("Пользователь = " + user);
        } catch (IOException e) {
            System.out.println("Проблема: = " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.println("Поиск всех пользователей");
        Driver[] drivers = driverService.findAllDriver();
        if(drivers !=null && drivers.length !=0 ){
            for(Driver user : drivers){
                System.out.println("Пользователь: " + user);
            }
        }else{
            System.out.println("Никого нет");
        }
    }
}
