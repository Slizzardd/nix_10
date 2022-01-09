package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.CarDao;
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

@Service
@Transactional
public class CarDaoImpl implements CarDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Car car) {
        entityManager.persist(car);
    }

    @Override
    public void update(Car car) {
        entityManager.merge(car);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Car s where s.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }


    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(c.id) from Car c where c.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Car findById(Long id) {
        return entityManager.find(Car.class, id);
    }

    @Override
    public DataTableResponse<Car> findAll(DataTableRequest request) {
        List<Car> cars;
        Map<Object, Object> otherParamMap = new HashMap<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> from = criteriaQuery.from(Car.class);
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        if (request.getSort().equals("cars_name") || request.getSort().equals("car_number")
                || request.getSort().equals("engine_of_capacity") || request.getSort().equals("years_of_issue")) {
            Query query;
            if (request.getOrder().equals("desc")) {
                query = entityManager.createQuery("select c from Car c where c.visible = true order by " + request.getSort() + " desc")
                        .setFirstResult(page)
                        .setMaxResults(size);
            } else {
                query = entityManager.createQuery("select c from Car c where c.visible = true order by " + request.getSort() + " asc")
                        .setFirstResult(page)
                        .setMaxResults(size);
            }
            cars = query.getResultList();
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

            cars = entityManager.createQuery(criteriaQuery)
                    .setFirstResult(page)
                    .setMaxResults(size)
                    .getResultList();
        }

        DataTableResponse<Car> response = new DataTableResponse<>();

        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        response.setCurrentPage(request.getCurrentPage());
        response.setCurrentSize(request.getPageSize());
        response.setItems(cars);
        response.setOtherParamMap(otherParamMap);

        return response;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(c) as count from Car as c");
        return (long) query.getSingleResult();
    }

    @Override
    public Map<Long, String> findDriversByCarId(Long carId) {
        List<Driver> driverList = findById(carId).getDrivers().stream().toList();
        Map<Long, String> drivers = new HashMap<>();
        for (int i = 0; i < driverList.size(); i++) {
            drivers.put(driverList.get(i).getId(), driverList.get(i).getFirstName() + " " + driverList.get(i).getLastName());
        }
        return drivers;
    }
}
