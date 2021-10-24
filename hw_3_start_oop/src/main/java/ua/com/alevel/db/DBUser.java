package ua.com.alevel.db;

import ua.com.alevel.entity.User;

import java.util.Scanner;

public class DBUser {
    private static User[] users = new User[5];


    public static void create(User user) {
        user.setSerialNumber(createSerialNumber());
        int userNumber = 0;
        for (User i : users) {
            if (i != null) {
                userNumber++;
            }
        }
        if (userNumber == users.length - 1) {
            User[] tempUser = new User[users.length + 5];
            System.arraycopy(users, 0, tempUser, 0, users.length);
            users = tempUser;
        } else {
            users[userNumber] = user;
        }
    }

    private static String createSerialNumber() {
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

    public static void update(User user) {
        User current = findBySerialNumber(user.getSerialNumber());
        current.setName(user.getName());
        current.setPhoneNumber(user.getPhoneNumber());
        current.setEmail(user.getEmail());
    }

    public static User findBySerialNumber(String serialNumber) {
        for (User user : users) {
            if (serialNumber.equals(user.getSerialNumber())) {
                return user;
            }
        }
        throw new RuntimeException("Такого серийного номера нет");
    }

    public static void delete(String serialNumber) {
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
