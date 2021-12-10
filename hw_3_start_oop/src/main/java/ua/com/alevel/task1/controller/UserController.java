package ua.com.alevel.task1.controller;

import ua.com.alevel.entity.User;
import ua.com.alevel.task1.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserController {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private final UserService userService = new UserService();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Программа баз данных машин(хранит текущего пользователя машины):)");
        System.out.println("Выберите действие: ");
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
        System.out.println("Если вы хотите выйти из программы, нажмите 0");
        System.out.println();
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findBySerialNumber(reader);
            case "5" -> findAll(reader);
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        System.out.println("Создание пользователя");
        try {
            System.out.println("Пожалуйста, введите имя: ");
            String name = reader.readLine();
            System.out.println("Пожалуйста, введите email: ");
            String email = reader.readLine();
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            boolean emailRegex =  matcher.find();
            System.out.println("emailRegex = " + emailRegex);
            System.out.println("Пожалуйста, введите номер мобильного телефона: ");
            String phoneNumber = reader.readLine();
            System.out.println("Пожалуйста, введите марку авто: ");
            String manufacturer = reader.readLine();
            System.out.println("Пожалуйста, введите модель авто: ");
            String brand = reader.readLine();
            System.out.println("Пожалуйста, введите год изготовления авто: ");
            String ageString = reader.readLine();
            int age = Integer.parseInt(ageString);
            User user = new User();
            user.setManufacturer(manufacturer);
            user.setBrand(brand);
            user.setYearOfIssue(age);
            user.setName(name);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            userService.create(user);
        } catch (IOException e) {
            System.out.println("проблема: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("Обновление пользователя");
        try {
            System.out.println("Введите серийный номер авто для поиска: ");
            String serialNumber = reader.readLine();
            System.out.println("Пожалуйста, введите имя: ");
            String name = reader.readLine();
            System.out.println("Пожалуйста, введите email: ");
            String email = reader.readLine();
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            boolean emailRegex =  matcher.find();
            System.out.println("emailRegex = " + emailRegex);
            System.out.println("Пожалуйста, введите номер мобильного телефона: ");
            String phoneNumber = reader.readLine();
            User user = new User();
            user.setSerialNumber(serialNumber);
            user.setName(name);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            userService.update(user);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("Удаление пользователя");
        try {
            System.out.println("Пожалуйста, введите серийный номер(VIM): ");
            String serialNumber = reader.readLine();
            userService.delete(serialNumber);
        } catch (IOException e) {
            System.out.println("Проблема: = " + e.getMessage());
        }
    }

    private void findBySerialNumber(BufferedReader reader) {
        System.out.println("Поиск по серийному номеру");
        try {
            System.out.println("Пожалуйста, введите серийный номер(VIM): ");
            String serialNumber = reader.readLine();
            User user = userService.findBySerialNumber(serialNumber);
            System.out.println("Пользователь = " + user);
        } catch (IOException e) {
            System.out.println("Проблема: = " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.println("Поиск всех пользователей");
        User[] users = userService.findAll();
        if(users !=null && users.length !=0 ){
            for(User user : users){
                System.out.println("Пользователь: " + user);
            }
        }else{
            System.out.println("Никого нет");
        }
    }
}