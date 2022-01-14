package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.dao.UserDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final AccountDao accountDao;
    private final UserDao userDao;
    public AccountServiceImpl(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @Override
    public void create(Account account, Long userId) {
        LOGGER_INFO.info("CREATE ACCOUNT(USER_ID): " + userId);
        if(userDao.existById(userId)){
            LOGGER_INFO.info("done");
            accountDao.create(account, userId);
        }else{
            LOGGER_INFO.warn("user not found");
            throw new EntityNotFoundException("User not found");
        }
    }

    @Override
    public void update(Account account) {
        LOGGER_INFO.info("UPDATE ACCOUNT(ACCOUNT_ID): " + account.getId());
        accountDao.update(account);
    }

    @Override
    public void delete(Long id) {
        LOGGER_INFO.info("DELETING ACCOUNT(ACCOUNT_ID): " + id);
        if(existById(id)){
            LOGGER_INFO.info("done");
            accountDao.delete(id);
        }else{
            LOGGER_INFO.warn("account not found");
            throw new EntityNotFoundException("Account not found");
        }
    }

    @Override
    public Map<Long, String> findUserByAccountId(Long accountId) {
        LOGGER_INFO.info("FIND MAP USER BY ACCOUNT ID: " + accountId);
        if(existById(accountId)){
            LOGGER_INFO.info("done");
            return accountDao.findUserByAccountId(accountId);
        }else{
            LOGGER_INFO.warn("user not found");
            throw new EntityNotFoundException("User is not found");
        }

    }

    @Override
    public List<Transaction> findAllTransactionByAccountId(Long accountId) {
        LOGGER_INFO.info("FIND ALL TRANSACTIONS BY ACCOUNT ID: " + accountId);
        if(existById(accountId)){
            LOGGER_INFO.info("done");
            return accountDao.findAllTransactionByAccountId(accountId);
        }else{
            LOGGER_INFO.warn("account not found");
            throw new EntityNotFoundException("Account is not found");
        }
    }

    @Override
    public void cardStatement(Long accountId) {
        accountDao.cardStatement(accountId);
    }

    @Override
    public boolean existById(Long id) {
        return accountDao.existById(id);
    }

    @Override
    public Account findById(Long id) {
        LOGGER_INFO.info("FIND ACCOUNT BY ID: " + id);
        if(existById(id)){
            LOGGER_INFO.info("done");
            return accountDao.findById(id);
        }else{
            LOGGER_INFO.warn("account not found");
            throw new EntityNotFoundException("User is not found");
        }
    }

    @Override
    public DataTableResponse<Account> findAll(DataTableRequest request) {
        LOGGER_INFO.info("FIND ALL ACCOUNTS");
        DataTableResponse<Account> dataTableResponse = accountDao.findAll(request);
        long count = accountDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }
}
