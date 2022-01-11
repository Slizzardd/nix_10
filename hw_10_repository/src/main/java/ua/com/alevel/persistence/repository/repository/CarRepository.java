package ua.com.alevel.persistence.repository.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends BaseRepository<Car> {

    @Query(value = "select c from Car c left join c.drivers dc where dc.id = :id")
    List<Car> findCarsByDriverId(@Param("id") Long id);

    @Query(value = "select count(c) from Car c")
    long countCars();

    @Transactional
    @Modifying
    @Query(value = "delete from Car с where с.id = :id")
    void delete(@Param("id") Long id);
}
