package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Transaction;

public interface TransactionService extends BaseService<Transaction> {

    void create(Transaction entity, String temporalField);


}
