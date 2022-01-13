package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.util.GenerateCardNumber;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
    public void create(Account account, String passportDetails, Double balance) {
        account.setCardNumber(GenerateCardNumber.createCardNumber());
        Query query = entityManager.createQuery("select u from User u where u.email = :passportDetails")
                .setParameter("passportDetails", passportDetails);
        User user = (User) query.getSingleResult();
        account.setBalance(getCreditBalanceByHistoryUsers(user, account));
        entityManager.persist(account);
        user.addAccount(account);
        entityManager.merge(user);
    }

    @Override
    public void update(Account account) {
        entityManager.merge(account);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Account a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
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
