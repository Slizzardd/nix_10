package ua.com.alevel.dao;

import ua.com.alevel.entity.User;
import ua.com.alevel.db.DBUser;

public class UserDao {

    public void create(User user) {
        DBUser.getInstance().create(user);
    }

    public void update(User user) {
        DBUser.getInstance().update(user);
    }

    public void delete(String serialNumber) {
        DBUser.getInstance().delete(serialNumber);
    }

    public User findBySerialNumber(String serialNumber) {
        return DBUser.getInstance().findBySerialNumber(serialNumber);
    }

    public User[] findAll() {
        return DBUser.getInstance().findAll();
    }
}
