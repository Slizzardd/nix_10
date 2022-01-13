package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.TransactionDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TransactionDaoImpl implements TransactionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Transaction transaction, String cardNumber) {
        Query query = entityManager.createQuery("select a from Account a where a.cardNumber = :cardNumber")
                .setParameter("cardNumber", cardNumber);
        Account account = (Account) query.getSingleResult();
        if(transaction.getCategory().getIncome()){
            account.setBalance(account.getBalance() + transaction.getAmount());
        }else if(account.getBalance() >= transaction.getAmount()){
            account.setBalance(account.getBalance() - transaction.getAmount());
        } else{
            throw new NullPointerException("NULL BALANCE!!!");  //TODO fix thi(add new exception)
        }
        transaction.setAccount(account);
        entityManager.persist(transaction);
        account.addTransaction(transaction);
        entityManager.merge(account);
    }


    @Override
    public boolean existById(Long id) {
        //It makes no sense
        return false;
    }

    @Override
    public Transaction findById(Long id) {
        return entityManager.find(Transaction.class, id);
    }

    @Override
    public DataTableResponse<Transaction> findAll(DataTableRequest request) {
        List<Transaction> transactions;
        Map<Object, Object> otherParamMap = new HashMap<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
        Root<Transaction> from = criteriaQuery.from(Transaction.class);
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        transactions = entityManager.createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();

        DataTableResponse<Transaction> response = new DataTableResponse<>();

        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        response.setCurrentPage(request.getCurrentPage());
        response.setCurrentSize(request.getPageSize());
        response.setItems(transactions);
        response.setOtherParamMap(otherParamMap);
        return response;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(t) as count from Transaction as t");
        return (long) query.getSingleResult();
    }
}
