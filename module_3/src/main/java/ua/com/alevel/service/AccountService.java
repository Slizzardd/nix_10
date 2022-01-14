package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Transaction;

import java.util.List;
import java.util.Map;

public interface AccountService extends BaseService<Account> {
    void create(Account entity, Long userId);

    void update(Account entity);

    void delete(Long id);

    Map<Long, String> findUserByAccountId(Long accountId);

    List<Transaction> findAllTransactionByAccountId(Long accountId);

    void cardStatement(Long accountId);
}
