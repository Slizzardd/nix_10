package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.UserDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public Map<Long, String> findAccountsByUserId(Long id) {
        if(existById(id)){
            List<Account> accountList = findById(id).getAccounts().stream().toList();
            Map<Long, String> accountsMap = new HashMap<>();
            for(Account account : accountList){
                accountsMap.put(account.getId(), account.getCardNumber());
            }
            return accountsMap;
        }else{
            throw new EntityNotFoundException("User not found");
        }
    }

    @Override
    public List<Account> findAllAccountsByUserId(Long userId) {
        if(existById(userId)){
            return findById(userId).getAccounts().stream().toList();
        }else{
            throw new EntityNotFoundException("User npt found");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(u.id) from User u where u.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public User findById(Long id) {
        if(existById(id)){
            return entityManager.find(User.class, id);
        }else{
            throw new EntityNotFoundException("User not found");
        }
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        List<User> users;
        Map<Object, Object> otherParamMap = new HashMap<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        if (request.getSort().equals("accountCount")) {
            Query query;
            if (request.getOrder().equals("desc")) {
                query = entityManager.createQuery("select u from User u where u.visible = true order by u.accounts.size desc")
                        .setFirstResult(page)
                        .setMaxResults(size);
            } else {
                query = entityManager.createQuery("select u from User u where u.visible = true order by u.accounts.size asc")
                        .setFirstResult(page)
                        .setMaxResults(size);
            }
            users = query.getResultList();
        } else if (request.getSort().equals("first_name")
                || request.getSort().equals("last_name")
                || request.getSort().equals("passport_details")) {
            Query query;
            if (request.getOrder().equals("desc")) {
                query = entityManager.createQuery("select u from User u where u.visible = true order by " + request.getSort() + " desc")
                        .setFirstResult(page)
                        .setMaxResults(size);
            } else {
                query = entityManager.createQuery("select u from User u where u.visible = true order by " + request.getSort() + " asc")
                        .setFirstResult(page)
                        .setMaxResults(size);
            }
            users = query.getResultList();
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

            users = entityManager.createQuery(criteriaQuery)
                    .setFirstResult(page)
                    .setMaxResults(size)
                    .getResultList();
        }

        for (User user : users) {
            otherParamMap.put(user.getId(), countNumOfAccounts(user.getId()));
        }
        DataTableResponse<User> response = new DataTableResponse<>();

        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        response.setCurrentPage(request.getCurrentPage());
        response.setCurrentSize(request.getPageSize());
        response.setItems(users);
        response.setOtherParamMap(otherParamMap);

        return response;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(u) as count from User as u");
        return (long) query.getSingleResult();
    }


    private int countNumOfAccounts(Long id) {
        return findById(id).getAccounts().size();
    }
}
