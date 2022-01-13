package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Transaction;

public interface TransactionDao extends BaseDao<Transaction> {

    void create(Transaction entity, String temporalField);
}
