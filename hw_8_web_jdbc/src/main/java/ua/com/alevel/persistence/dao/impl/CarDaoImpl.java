package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.CarDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class CarDaoImpl implements CarDao {

    private final JpaConfig jpaConfig;

    private final static String CREATE_CAR_QUERY = "insert into cars values(default,?,?,?,?,?,?,?,?,?)";
    private final static String UPDATE_CAR_QUERY = "update cars set cars_name = ?, image_url = ?, color = ?, years_of_issue = ?, engine_of_capacity = ?, updated = ?, image_url = ?, where id = ";
    private final static String DELETE_CARS_BY_ID_QUERY = "delete from cars where id = ";
    private final static String EXIST_CARS_BY_ID_QUERY = "select count(*) from cars where id = ";
    private final static String FIND_CARS_BY_ID_QUERY = "select * from cars where id = ";
    private final static String FIND_ALL_CARS_QUERY = "select * from cars";
    private final static String FIND_ALL_SIMPLE_CARS_BY_DRIVER_ID_QUERY = "select id, cars_name, color, years_of_issue, engine_of_capacity from cars left join driver_car ab on cars.id = ab.car_id where ab.driver_id = ";
    private final static String FIND_ALL_CARS_BY_DRIVER_ID_QUERY = "select * from cars left join driver_car ab on cars.id = ab.car_id where ab.driver_id = ";

    public CarDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Car entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_CAR_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.getVisible());
            preparedStatement.setString(4, entity.getCarName());
            preparedStatement.setString(5, entity.getImageUrl());
            preparedStatement.setString(6, entity.getColor());
            preparedStatement.setInt(7, entity.getYearsOfIssue());
            preparedStatement.setDouble(8, entity.getEngineCapacity());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Car entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_CAR_QUERY + entity.getId())) {
            preparedStatement.setString(1, entity.getCarName());
            preparedStatement.setString(2, entity.getImageUrl());
            preparedStatement.setString(3, entity.getColor());
            preparedStatement.setInt(4, entity.getYearsOfIssue());
            preparedStatement.setDouble(5, entity.getEngineCapacity());
            preparedStatement.setTimestamp(6, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setString(7, entity.getImageUrl());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_CARS_BY_ID_QUERY + id)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_CARS_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 1;
    }

    @Override
    public Car findById(Long id) {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_CARS_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                return initCarByResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DataTableResponse<Car> findAll(DataTableRequest request) {
        List<Car> cars = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_CARS_QUERY)) {
            while (resultSet.next()) {
                cars.add(initCarByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<Car> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(cars);
        return dataTableResponse;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Map<Long, String> findByDriverId(Long driverId) {
        Map<Long, String> map = new HashMap<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_SIMPLE_CARS_BY_DRIVER_ID_QUERY + driverId)) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String carName = resultSet.getString("cars_name");
                map.put(id, carName);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return map;
    }

    @Override
    public List<Car> findAllDriverId(Long id) {
        List<Car> cars = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_CARS_BY_DRIVER_ID_QUERY + id)) {
            while (resultSet.next()) {
                cars.add(initCarByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return cars;
    }

    private Car initCarByResultSet(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("id");
            Timestamp created = resultSet.getTimestamp("created");
            Timestamp updated = resultSet.getTimestamp("updated");
            String carName = resultSet.getString("cars_name");
            Boolean visible = resultSet.getBoolean("visible");
            String imageUrl = resultSet.getString("image_url");
            String color = resultSet.getString("color");
            Integer yearsOfIssue = resultSet.getInt("years_of_issue");
            double engineCapacity = resultSet.getDouble("engine_of_capacity");

            Car car = new Car();
            car.setId(id);
            car.setCreated(new Date(created.getTime()));
            car.setUpdated(new Date(updated.getTime()));
            car.setCarName(carName);
            car.setVisible(visible);
            car.setImageUrl(imageUrl);
            car.setColor(color);
            car.setYearsOfIssue(yearsOfIssue);
            car.setEngineCapacity(engineCapacity);
            return car;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
