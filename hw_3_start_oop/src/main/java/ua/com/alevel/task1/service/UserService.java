package ua.com.alevel.task1.service;

import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;

public class UserService {
    private final UserDao userDao = new UserDao();

    public void create(User user) {
        userDao.create(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(String serialNumber) {
        userDao.delete(serialNumber);
    }

    public User findBySerialNumber(String serialNumber) {
        return userDao.findBySerialNumber(serialNumber);
    }

    public User[] findAll() {
        return userDao.findAll();
    }
}
