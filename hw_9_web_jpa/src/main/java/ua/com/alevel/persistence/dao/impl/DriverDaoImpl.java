package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public DataTableResponse<Driver> findAll(DataTableRequest request) {
        List<Driver> drivers;
        Map<Object, Object> otherParamMap = new HashMap<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Driver> criteriaQuery = criteriaBuilder.createQuery(Driver.class);
        Root<Driver> from = criteriaQuery.from(Driver.class);
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        if (request.getSort().equals("carCount")) {
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
        } else if (request.getSort().equals("first_name") || request.getSort().equals("last_name")) {
            Query query;
            if (request.getOrder().equals("desc")) {
                query = entityManager.createQuery("select d from Driver d where d.visible = true order by " + request.getSort() + " desc")
                        .setFirstResult(page)
                        .setMaxResults(size);
            } else {
                query = entityManager.createQuery("select d from Driver d where d.visible = true order by " + request.getSort() + " asc")
                        .setFirstResult(page)
                        .setMaxResults(size);
            }
            drivers = query.getResultList();
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

            drivers = entityManager.createQuery(criteriaQuery)
                    .setFirstResult(page)
                    .setMaxResults(size)
                    .getResultList();
        }

        for (Driver driver : drivers) {
            otherParamMap.put(driver.getId(), countNumOfCars(driver.getId()));
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
    public Map<Long, String> findCarsByDriverId(Long driverId) {
        List<Car> carsList = findById(driverId).getCars().stream().toList();
        Map<Long, String> cars = new HashMap<>();
        for (Car car : carsList) {
            cars.put(car.getId(), car.getCarName() + " " + car.getCarNumber());
        }
        return cars;
    }

    @Override
    public List<Car> findAllCarsByDriverId(Long driverId) {
        return findById(driverId).getCars().stream().toList();
    }

    @Override
    public void createRelation(Long driverId, Car car) throws NullPointerException {
        Driver driver = findById(driverId);
        driver.addCar(car);
        update(driver);
    }
}
