package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.CategoryDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Category;

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
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Category category) {
        entityManager.persist(category);
    }


    @Override
    public boolean existById(Long id) {
        //It makes no sense
        return false;
    }

    @Override
    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public DataTableResponse<Category> findAll(DataTableRequest request) {
        List<Category> categories;
        Map<Object, Object> otherParamMap = new HashMap<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
        Root<Category> from = criteriaQuery.from(Category.class);
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        if (request.getSort().equals("transactionCount")) {
            Query query;
            if (request.getOrder().equals("desc")) {
                query = entityManager.createQuery("select c from Category c where c.visible = true order by c.transactions.size desc")
                        .setFirstResult(page)
                        .setMaxResults(size);
            } else {
                query = entityManager.createQuery("select c from Category c where c.visible = true order by c.transactions.size asc")
                        .setFirstResult(page)
                        .setMaxResults(size);
            }
            categories = query.getResultList();
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

            categories = entityManager.createQuery(criteriaQuery)
                    .setFirstResult(page)
                    .setMaxResults(size)
                    .getResultList();
        }
        for (Category category : categories) {
            otherParamMap.put(category.getId(), countNumOfTransaction(category.getId()));
        }
        DataTableResponse<Category> response = new DataTableResponse<>();

        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        response.setCurrentPage(request.getCurrentPage());
        response.setCurrentSize(request.getPageSize());
        response.setItems(categories);
        response.setOtherParamMap(otherParamMap);
        return response;
    }

    private long countNumOfTransaction(Long id) {
        return findById(id).getTransactions().size();
    }

    @Override
    public long count() {
        return 0;
    }
}
