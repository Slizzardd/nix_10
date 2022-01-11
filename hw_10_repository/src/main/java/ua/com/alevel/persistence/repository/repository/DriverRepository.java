package ua.com.alevel.persistence.repository.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.Driver;

import java.util.List;

@Repository
public interface DriverRepository extends BaseRepository<Driver> {

    @Query(value = "select d from Driver d left join d.cars dc where dc.id = :id")
    List<Driver> findDriversByCarId(@Param("id") Long id);

    @Query(value = "select count(d) from Driver d")
    long countDrivers();

    @Transactional
    @Modifying
    @Query(value = "delete from Driver d where d.id = :id")
    void delete(@Param("id") Long id);
}
