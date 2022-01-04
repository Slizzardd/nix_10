package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.DriverDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Driver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriverDaoImpl implements DriverDao {

    private final JpaConfig jpaConfig;

    private static final String CREATE_DRIVER_QUERY = "insert into drivers values(default, ?,?,?,?,?,?,?,?)";
    private static final String UPDATE_DRIVER_QUERY = "update drivers set updated = ?, first_name = ?, last_name = ?, balance = ?, notes = ?, image_url = ? where id = ";
    private static final String DELETE_DRIVER_BY_ID_QUERY = "delete from drivers where id = ";
    private static final String EXIST_DRIVERS_BY_ID_QUERY = "select count(*) from drivers where id = ";
    private static final String FIND_DRIVER_BY_ID_QUERY = "select * from drivers where id = ";
    private static final String FIND_DRIVER_BY_CAR_ID_QUERY = "select id, first_name, last_name from drivers left join driver_car ab on drivers.id = ab.driver_id where car_id = ";
    public DriverDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Driver entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_DRIVER_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.getVisible());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getLastName());
            preparedStatement.setDouble(6, entity.getBalance());
            preparedStatement.setString(7, entity.getNotes());
            preparedStatement.setString(8, entity.getImageUrl());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void update(Driver entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_DRIVER_QUERY + entity.getId())) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setString(2, entity.getFirstName());
            preparedStatement.setString(3, entity.getLastName());
            preparedStatement.setDouble(4, entity.getBalance());
            preparedStatement.setString(5, entity.getNotes());
            preparedStatement.setString(6, entity.getImageUrl());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_DRIVER_BY_ID_QUERY + id)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_DRIVERS_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 1;
    }

    @Override
    public Driver findById(Long id) {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_DRIVER_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return convertResultSetToDriver(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DataTableResponse<Driver> findAll(DataTableRequest request) {
        List<Driver> drivers = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select id, first_name, last_name, count(driver_id) as carCount, image_url, notes, balance " +
                "from drivers as driver left join driver_car as ab on driver.id = ab.driver_id " +
                "group by driver.id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();


        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                DriverResultSet driverResultSet = convertResultSetToSimpleDriver(resultSet);
                drivers.add(driverResultSet.getDriver());
                otherParamMap.put(driverResultSet.getDriver().getId(), driverResultSet.getCarCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataTableResponse<Driver> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(drivers);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public long count() {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from drivers")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Map<Long, String> findAllByCarId(Long carId) {
        Map<Long, String> map = new HashMap<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_DRIVER_BY_CAR_ID_QUERY + carId)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                map.put(id, firstName + " " + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    private DriverResultSet convertResultSetToSimpleDriver(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int carCount = resultSet.getInt("carCount");
        String imageUrl = resultSet.getString("image_url");
        double balance = resultSet.getDouble("balance");

        Driver driver = new Driver();
        driver.setId(id);
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setImageUrl(imageUrl);
        driver.setBalance(balance);
        return new DriverResultSet(driver, carCount);
    }

    private Driver convertResultSetToDriver(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        double balance = resultSet.getDouble("balance");
        String notes = resultSet.getString("notes");
        String imageUrl = resultSet.getString("image_url");

        Driver driver = new Driver();
        driver.setId(id);
        driver.setCreated(created);
        driver.setUpdated(updated);
        driver.setVisible(visible);
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setBalance(balance);
        driver.setBalance(balance);
        driver.setNotes(notes);
        driver.setImageUrl(imageUrl);
        return driver;
    }

    private static class DriverResultSet{
        private final Driver driver;
        private final int carCount;

        private DriverResultSet(Driver driver, int carCount) {
            this.driver = driver;
            this.carCount = carCount;
        }

        public Driver getDriver() {
            return driver;
        }

        public int getCarCount() {
            return carCount;
        }
    }
}
