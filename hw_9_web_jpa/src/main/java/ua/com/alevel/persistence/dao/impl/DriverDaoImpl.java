package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.DriverDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Car;
import ua.com.alevel.persistence.entity.Driver;

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
import java.util.Set;

@Service
@Transactional
public class DriverDaoImpl implements DriverDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Driver driver) {
        entityManager.persist(driver);
    }

    @Override
    public void update(Driver driver) {
        entityManager.merge(driver);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Driver d where d.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void delete(Driver driver) {
        entityManager.refresh(driver);
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(d.id) from Driver d where d.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Driver findById(Long id) {
        return entityManager.find(Driver.class, id);
    }

    @Override
    public DataTableResponse<Driver> findAll(DataTableRequest request) throws Exception {
        List<Driver> drivers;
        Map<Object, Object> otherParamMap = new HashMap<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Driver> criteriaQuery = criteriaBuilder.createQuery(Driver.class);
        Root<Driver> from = criteriaQuery.from(Driver.class);
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        if(request.getSort().equals("carCount")){
            Query query;
            if (request.getOrder().equals("desc")) {
                query = entityManager.createQuery("select d from Driver d where d.visible = true order by d.cars.size desc")
                        .setFirstResult(page)
                        .setMaxResults(size);
            } else {
                query = entityManager.createQuery("select d from Driver d where d.visible = true order by d.cars.size asc")
                        .setFirstResult(page)
                        .setMaxResults(size);
            }
            drivers = query.getResultList();
        }else {
            try{
                if (request.getOrder().equals("desc")) {
                    criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
                }
            }catch (Exception e){
                e.printStackTrace();;
            }

            drivers = entityManager.createQuery(criteriaQuery)
                    .setFirstResult(page)
                    .setMaxResults(size)
                    .getResultList();
        }

        for (int i = 0; i < drivers.size(); i++) {
            otherParamMap.put(drivers.get(i).getId(), countNumOfCars(drivers.get(i).getId()));
        }
        DataTableResponse<Driver> response = new DataTableResponse<>();

        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        response.setCurrentPage(request.getCurrentPage());
        response.setCurrentSize(request.getPageSize());
        response.setItems(drivers);
        response.setOtherParamMap(otherParamMap);

        return response;
    }

    private int countNumOfCars(Long id) {
        return findById(id).getCars().size();
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(d) as count from Driver as d");
        return (long) query.getSingleResult();
    }

    @Override
    public Map<Long, String> findAllByCarId(Long carId) {
        Map<Long, String> map = new HashMap<>();
        Set<Car> cars = findById(carId).getCars();
        for (Car car : cars) {
            map.put(car.getId(), car.getCarName());
        }
        return map;
    }
}
