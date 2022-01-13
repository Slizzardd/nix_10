package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.UserDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.UserService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void create(User user) {
        userDao.create(user); //TODO добавить проверку на существование такого пользователя
    }

    @Override
    public void update(User user) {
        userDao.update(user); //TODO добавить проверку на существование такого пользователя
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public Map<Long, String> findAccountsByUserId(Long id) {
        return userDao.findAccountsByUserId(id);
    }

    @Override
    public List<Account> findAllAccountsByUserId(Long userId) {
        return userDao.findAllAccountsByUserId(userId);
    }

    @Override
    public boolean existById(Long id) {
        return userDao.existById(id);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        DataTableResponse<User> dataTableResponse = userDao.findAll(request);
        long count = userDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }
}
