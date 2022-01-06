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
    private final static String CREATE_DRIVER_CAR_QUERY = "insert into driver_car values(?,?)";
    private final static String FIND_CAR_ID_BY_CAR_NUMBER_QUERY = "select id from cars where car_number = ?";
    private final static String UPDATE_CAR_QUERY = "update cars set cars_name = ?, image_url = ?, color = ?, years_of_issue = ?, engine_of_capacity = ?, updated = ?, car_number = ? where id = ";
    private final static String DELETE_DRIVER_CAR_BY_CAR_ID_QUERY = "delete from driver_car where car_id = ";
    private final static String DELETE_CAR_BY_ID_QUERY = "delete from cars where id = ";
    private final static String EXIST_CARS_BY_ID_QUERY = "select count(*) from cars where id = ";
    private final static String FIND_CARS_BY_ID_QUERY = "select * from cars where id = ";
    private final static String FIND_ALL_CARS_QUERY = "select * from cars";
    private final static String FIND_ALL_SIMPLE_CARS_BY_DRIVER_ID_QUERY = "select id, cars_name, color, years_of_issue, engine_of_capacity, car_number from cars left join driver_car ab on cars.id = ab.car_id where ab.driver_id = ";
    private final static String FIND_ALL_CARS_BY_DRIVER_ID_QUERY = "select * from cars left join driver_car ab on cars.id = ab.car_id where ab.driver_id = ";
    private final static String FIND_ID_ALL_CARS_BY_DRIVER_ID_QUERY = "select id from cars left join driver_car ab on cars.id = ab.car_id where ab.driver_id = ";


    public CarDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Car entity, Long driverId) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_CAR_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.getVisible());
            preparedStatement.setString(4, entity.getCarName());
            preparedStatement.setString(5, entity.getImageUrl());
            preparedStatement.setString(6, entity.getColor());
            preparedStatement.setInt(7, entity.getYearsOfIssue());
            preparedStatement.setDouble(8, entity.getEngineCapacity());
            preparedStatement.setString(9, entity.getCarNumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        createRelationShip(entity, driverId);
    }

    @Override
    public void update(Car entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_CAR_QUERY + entity.getId())) {
            preparedStatement.setString(1, entity.getCarName());
            preparedStatement.setString(2, entity.getImageUrl());
            preparedStatement.setString(3, entity.getColor());
            preparedStatement.setInt(4, entity.getYearsOfIssue());
            preparedStatement.setDouble(5, entity.getEngineCapacity());
            preparedStatement.setTimestamp(6, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setString(7, entity.getCarNumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_DRIVER_CAR_BY_CAR_ID_QUERY + id)) {
            preparedStatement.execute();
            try (PreparedStatement prepared = jpaConfig.getConnection().prepareStatement(DELETE_CAR_BY_ID_QUERY + id)) {
                prepared.execute();
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_CARS_BY_ID_QUERY + id)) {
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
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_CARS_BY_ID_QUERY + id)) {
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
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select id, cars_name, count(car_id) as driverCount, image_url, color, years_of_issue, engine_of_capacity, car_number " +
                "from cars as car left join driver_car as ab on car.id = ab.car_id " +
                "group by car.id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();


        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                CarResultSet carResultSet = convertResultSetToSimpleCar(resultSet);
                cars.add(carResultSet.getCar());
                otherParamMap.put(carResultSet.getCar().getId(), carResultSet.getDriverCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataTableResponse<Car> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(cars);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    private CarResultSet convertResultSetToSimpleCar(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String carName = resultSet.getString("cars_name");
        int driverCount = resultSet.getInt("driverCount");
        String imageUrl = resultSet.getString("image_url");
        String color = resultSet.getString("color");
        Integer yearsOfIssue = resultSet.getInt("years_of_issue");
        double engineCapacity = resultSet.getDouble("engine_of_capacity");
        String carNumber = resultSet.getString("car_number");

        Car car = new Car();
        car.setId(id);
        car.setCarName(carName);
        car.setImageUrl(imageUrl);
        car.setColor(color);
        car.setYearsOfIssue(yearsOfIssue);
        car.setEngineCapacity(engineCapacity);
        car.setCarNumber(carNumber);
        return new CarResultSet(car, driverCount);
    }

    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from cars")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Map<Long, String> findByDriverId(Long driverId) {
        Map<Long, String> map = new HashMap<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_SIMPLE_CARS_BY_DRIVER_ID_QUERY + driverId)) {
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
    public List<Long> findAllCarByDriverId(Long driverId) {
        List<Long> carsId = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ID_ALL_CARS_BY_DRIVER_ID_QUERY + driverId)) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                carsId.add(id);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return carsId;
    }

    @Override
    public List<Car> findAllDriverId(Long id) {
        List<Car> cars = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_CARS_BY_DRIVER_ID_QUERY + id)) {
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
            String carNumber = resultSet.getString("car_number");

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
            car.setCarNumber(carNumber);
            return car;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void createRelationShip(Car entity, Long driverId) {
        long carId = 0L;
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(FIND_CAR_ID_BY_CAR_NUMBER_QUERY)) {
            preparedStatement.setString(1, entity.getCarNumber());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    carId = resultSet.getLong("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }


        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_DRIVER_CAR_QUERY)) {
            preparedStatement.setLong(1, driverId);
            preparedStatement.setLong(2, carId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private record CarResultSet(Car car, int driverCount) {

        public Car getCar() {
            return car;
        }

        public int getDriverCount() {
            return driverCount;
        }
    }
}
