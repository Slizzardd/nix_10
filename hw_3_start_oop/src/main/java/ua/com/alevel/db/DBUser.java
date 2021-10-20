package ua.com.alevel.db;
import ua.com.alevel.entity.User;

import java.util.Arrays;
import java.util.Scanner;

public class DBUser {
    private static DBUser instance;
    private User[] users;

    private DBUser(){
        users = new User[0];
    }

    public static DBUser getInstance(){
        if(instance == null){
            instance = new DBUser();
        }
        return instance;
    }

    private void arrayUpdateToPlus(){
        users = Arrays.copyOf(users, users.length + 1);
    }
    private void arrayUpdateToMinus(){
        User [] userArray = Arrays.copyOf(users, users.length);
        users = new User [userArray.length - 1];
        int j = 0;
        for (User user : userArray) {
            if (user != null) {
                users[j] = user;
                j += 1;
            }
        }
    }

    public void create(User user){
        arrayUpdateToPlus();
        user.setSerialNumber(createSerialNumber());
        users[users.length - 1] = user;
    }

    private String createSerialNumber(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите серийный номер вашей машины(VIN): ");
        String serialNumber = scanner.nextLine();


        for(int i = 0; i < users.length - 1; i++)
        {
            if(serialNumber.equals(users[i].getSerialNumber()))
            {
                createSerialNumber();
            }

        }
        return serialNumber;
    }

    public void update(User user){
        User current = findBySerialNumber(user.getSerialNumber());
        current.setName(user.getName());
        current.setPhoneNumber(user.getPhoneNumber());
        current.setEmail(user.getEmail());
    }
    public User findBySerialNumber(String serialNumber){
        for (User user : users) {
            if (serialNumber.equals(user.getSerialNumber())) {
                return user;
            }
        }
        throw new RuntimeException("Такого серийного номера нет");
    }

    public void delete(String serialNumber){
        for(int i = 0; i < users.length; i++){
            if(serialNumber.equals(users[i].getSerialNumber())){
                users[i] = null;
            }
        }
        arrayUpdateToMinus();
    }

    public User [] findAll(){
        return users;
    }

}
