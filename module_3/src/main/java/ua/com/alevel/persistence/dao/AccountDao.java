package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Account;

import java.util.Map;

public interface AccountDao extends BaseDao<Account> {

    void update(Account entity);

    void delete(Long id);

    void create(Account entity, Long userId);

    Map<Long, String> findUserByAccountId(Long accountId);
}
