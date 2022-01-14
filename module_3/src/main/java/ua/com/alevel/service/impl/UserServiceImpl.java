package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
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

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void create(User user) {
        LOGGER_INFO.info("CREATING USER(USER): " + user.getFirstName() + " " + user.getLastName());
        userDao.create(user); //TODO добавить проверку на существование такого пользователя
    }

    @Override
    public void update(User user) {
        LOGGER_INFO.info("UPDATE USER(USER_UD): " + user.getId());
        userDao.update(user); //TODO добавить проверку на существование такого пользователя
    }

    @Override
    public void delete(Long id) {
        LOGGER_INFO.info("DELETING USER(USER_ID): " + id);
        if(existById(id)){
            LOGGER_INFO.info("done");
            userDao.delete(id);
        }else{
            LOGGER_INFO.warn("user not found");
            throw new EntityNotFoundException("User not found");
        }
    }

    @Override
    public Map<Long, String> findAccountsByUserId(Long userId) {
        LOGGER_INFO.info("FIND MAP ACCOUNTS BY USER ID: " + userId);
        if(existById(userId)){
            LOGGER_INFO.info("done");
            return userDao.findAccountsByUserId(userId);
        }else{
            LOGGER_INFO.warn("user not found");
            throw new EntityNotFoundException("User not found");
        }
    }

    @Override
    public List<Account> findAllAccountsByUserId(Long userId) {
        LOGGER_INFO.info("FIND LIST ACCOUNTS BY USER ID: " + userId);
        if(existById(userId)){
            LOGGER_INFO.info("done");
            return userDao.findAllAccountsByUserId(userId);
        }else{
            LOGGER_INFO.warn("user not found");
            throw new EntityNotFoundException("User not found");
        }
    }

    @Override
    public boolean existById(Long id) {
        return userDao.existById(id);
    }

    @Override
    public User findById(Long id) {
        LOGGER_INFO.info("FIND USER BY ID: " + id);
        if(existById(id)){
            LOGGER_INFO.info("done");
            return userDao.findById(id);
        }else{
            LOGGER_INFO.warn("user not found");
            throw new EntityNotFoundException("User not found");
        }
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        LOGGER_INFO.info("FIND ALL USERS");
        DataTableResponse<User> dataTableResponse = userDao.findAll(request);
        long count = userDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }
}
