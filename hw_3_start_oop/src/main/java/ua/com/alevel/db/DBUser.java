package ua.com.alevel.db;

import ua.com.alevel.persistence.entity.User;

import java.util.Scanner;

public class DBUser {
    private static User[] users = new User[5];
    private static DBUser instance;
    private int userSize = 0;

    public static DBUser getInstance() {
        if (instance == null) {
            instance = new DBUser();
        }
        return instance;
    }

    public void create(User user) {
        user.setSerialNumber(createSerialNumber());
        copyArray(user);
        userSize++;
    }

    private void copyArray(User user) {
        users[userSize] = user;
        if (userSize == users.length - 1) {
            users[userSize] = user;
            User[] tempUser = new User[users.length + 5];
            System.arraycopy(users, 0, tempUser, 0, users.length);
            users = tempUser;
        }
    }

    private String createSerialNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите серийный номер вашей машины(VIN): ");
        String serialNumber = scanner.nextLine();
        for (User user : users) {
            if (user != null && user.getSerialNumber().equals(serialNumber)) {
                return createSerialNumber();
            }
        }
        return serialNumber;
    }

    public void update(User user) {
        User current = findBySerialNumber(user.getSerialNumber());
        current.setName(user.getName());
        current.setPhoneNumber(user.getPhoneNumber());
        current.setEmail(user.getEmail());
    }

    public User findBySerialNumber(String serialNumber) {
        for (User user : users) {
            if (serialNumber.equals(user.getSerialNumber())) {
                return user;
            }
        }
        throw new RuntimeException("Такого серийного номера нет");
    }

    public void delete(String serialNumber) {
        for (int i = 0; i < users.length; i++) {
            if (serialNumber.equals(users[i].getSerialNumber())) {
                for (int p = i; p < users.length; p++) {
                    if (p != users.length - 1) {
                        users[p] = users[p + 1];
                    } else {
                        users[p] = null;
                    }
                }
                break;
            }
        }
    }

    public static User[] findAll() {
        return users;
    }

}