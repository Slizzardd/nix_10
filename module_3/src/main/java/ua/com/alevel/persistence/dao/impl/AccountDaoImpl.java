package ua.com.alevel.persistence.dao.impl;

import au.com.bytecode.opencsv.CSVWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.util.GenerateCardNumber;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.util.GetCreditBalanceByUserStatistic.getCreditBalanceByHistoryUsers;

@Service
@Transactional
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Account account, Long id) {
        account.setCardNumber(GenerateCardNumber.createCardNumber());
        Query query = entityManager.createQuery("select u from User u where u.id = :id")
                .setParameter("id", id);
        User user = (User) query.getSingleResult();
        user.addAccount(account);
        entityManager.merge(user);
        account.setBalance(getCreditBalanceByHistoryUsers(user, account));
        entityManager.persist(account);
    }

    @Override
    public Map<Long, String> findUserByAccountId(Long accountId) {
        User user = findById(accountId).getUser();
        Map<Long, String> map = new HashMap<>();
        map.put(user.getId(), user.getFirstName() + " " + user.getLastName());
        return map;
    }


    @Override
    public List<Transaction> findAllTransactionByAccountId(Long accountId) {
        return findById(accountId).getTransactions().stream().toList();
    }

    @Override
    public void cardStatement(Long accountId) {
        Account account = findById(accountId);
        List<Transaction> transactionList = account.getTransactions().stream().toList();
        List<String[]> writeList = new ArrayList<>();
        String[] accountHeader = {"Card Number", "Balance", "Card Type"};
        String[] accountData = {account.getCardNumber(), account.getBalance().toString()
                ,account.getCardType().toString()};
        String[] transactionHeader = {"Transaction id", "Amount", "Appointment", "Date"};
        writeList.add(accountHeader);
        writeList.add(accountData);
        writeList.add(transactionHeader);
        File f = new File("module_3/src/main/resources/files/data.csv");
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter(f))){
            for (Transaction transaction : transactionList) {
                String[] transactionWrite = {
                        transaction.getId().toString(),
                        transaction.getAmount().toString(),
                        transaction.getCategory().getName(),
                        String.valueOf(transaction.getCreated())
                };
                writeList.add(transactionWrite);
            }
            csvWriter.writeAll(writeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Account account) {
        account.setBalance(getCreditBalanceByHistoryUsers(account.getUser(), account));
        entityManager.merge(account);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(a.id) from Account a where a.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Account findById(Long id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public DataTableResponse<Account> findAll(DataTableRequest request) {
        List<Account> accounts;
        Map<Object, Object> otherParamMap = new HashMap<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> from = criteriaQuery.from(Account.class);
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        if (request.getSort().equals("transactionCount")) {
            Query query;
            if (request.getOrder().equals("desc")) {
                query = entityManager.createQuery("select a from Account a where a.visible = true order by a.transactions.size desc")
                        .setFirstResult(page)
                        .setMaxResults(size);
            } else {
                query = entityManager.createQuery("select a from Account a where a.visible = true order by a.transactions.size asc")
                        .setFirstResult(page)
                        .setMaxResults(size);
            }
            accounts = query.getResultList();
        } else {
            try {
                if (request.getOrder().equals("desc")) {
                    criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            accounts = entityManager.createQuery(criteriaQuery)
                    .setFirstResult(page)
                    .setMaxResults(size)
                    .getResultList();
        }
        for (Account account : accounts) {
            otherParamMap.put(account.getId(), countNumOfTransaction(account.getId()));
        }
        DataTableResponse<Account> response = new DataTableResponse<>();

        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        response.setCurrentPage(request.getCurrentPage());
        response.setCurrentSize(request.getPageSize());
        response.setItems(accounts);
        response.setOtherParamMap(otherParamMap);
        return response;
    }

    private Object countNumOfTransaction(Long id) {
        return findById(id).getTransactions().size();
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(a) as count from Account as a");
        return (long) query.getSingleResult();
    }
}
