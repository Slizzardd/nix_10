package ua.com.alevel.dao;

import ua.com.alevel.entity.User;
import ua.com.alevel.db.DBUser;

public class UserDao {
    private final DBUser usersDB = new DBUser();

    public void create(User user) {
        DBUser.create(user);
    }

    public void update(User user) {
        DBUser.update(user);
    }

    public void delete(String serialNumber) {
        DBUser.delete(serialNumber);
    }

    public User findBySerialNumber(String serialNumber) {
        return DBUser.findBySerialNumber(serialNumber);
    }

    public User[] findAll() {
        return DBUser.findAll();
    }
}
