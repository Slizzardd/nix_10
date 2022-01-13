package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService extends BaseService<User>{

    void create(User user);

    void update(User entity);

    void delete(Long id);

    Map<Long, String> findAccountsByUserId(Long id);

    List<Account> findAllAccountsByUserId(Long userId);
}
