package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao extends BaseDao<User> {

    void create(User user);

    void update(User entity);

    void delete(Long id);

    Map<Long, String> findAccountsByUserId(Long id);

    List<Account> findAllAccountsByUserId(Long userId);
}
