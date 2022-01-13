package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Account;

public interface AccountService extends BaseService<Account> {
    void create(Account entity, String temporalField);
}
