package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Account;

public interface AccountDao extends BaseDao<Account> {

    void create(Account entity, String temporalField, Double balance);
}
