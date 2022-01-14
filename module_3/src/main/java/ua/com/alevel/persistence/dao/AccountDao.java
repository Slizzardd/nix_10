package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Transaction;

import java.util.List;
import java.util.Map;

public interface AccountDao extends BaseDao<Account> {

    void update(Account entity);

    void delete(Long id);

    void create(Account entity, Long userId);

    Map<Long, String> findUserByAccountId(Long accountId);

    List<Transaction> findAllTransactionByAccountId(Long accountId);

    void cardStatement(Long accountId);
}
