package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void create(Account account, Long userId) {
        accountDao.create(account, userId);
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    public void delete(Long id) {
        accountDao.delete(id);
    }

    @Override
    public Map<Long, String> findUserByAccountId(Long accountId) {
        return accountDao.findUserByAccountId(accountId);
    }

    @Override
    public boolean existById(Long id) {
        return accountDao.existById(id);
    }

    @Override
    public Account findById(Long id) {
        return accountDao.findById(id);
    }

    @Override
    public DataTableResponse<Account> findAll(DataTableRequest request) {
        DataTableResponse<Account> dataTableResponse = accountDao.findAll(request);
        long count = accountDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }
}
