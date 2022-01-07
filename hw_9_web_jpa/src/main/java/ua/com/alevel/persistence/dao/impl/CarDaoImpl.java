package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.CarDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Car;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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
    public void update(Car entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Car entity) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Car findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Car> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Map<Long, String> findByDriverId(Long id) {
        return null;
    }

    @Override
    public List<Car> findAllDriverId(Long id) {
        return null;
    }

    @Override
    public List<Long> findAllCarByDriverId(Long id) {
        return null;
    }
}
