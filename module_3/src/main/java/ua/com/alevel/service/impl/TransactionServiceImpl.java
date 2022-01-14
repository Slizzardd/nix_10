package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.dao.TransactionDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.service.TransactionService;
import ua.com.alevel.util.WebResponseUtil;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final TransactionDao transactionDao;
    private final AccountDao accountDao;

    public TransactionServiceImpl(TransactionDao transactionDao, AccountDao accountDao) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
    }

    @Override
    public void create(Transaction transaction, String cardNumber) {
        LOGGER_INFO.info("CREATING TRANSACTION(CARD_NUMBER): " + cardNumber);
        transactionDao.create(transaction, cardNumber);
    }

    @Override
    public boolean existById(Long id) {
        return transactionDao.existById(id);
    }

    @Override
    public Transaction findById(Long id) {
        LOGGER_INFO.info("FIND TRANSACTION BY ID: " + id);
        if (existById(id)) {
            LOGGER_INFO.info("done");
            return transactionDao.findById(id);
        } else {
            LOGGER_INFO.warn("transaction not found");
            throw new EntityNotFoundException("Transaction Not Found");
        }
    }

    @Override
    public DataTableResponse<Transaction> findAll(DataTableRequest request) {
        LOGGER_INFO.info("FIND ALL TRANSACTIONS");
        DataTableResponse<Transaction> dataTableResponse = transactionDao.findAll(request);
        long count = transactionDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }
}
