package ua.com.alevel;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.alevel.persistence.dao.CarDao;
import ua.com.alevel.persistence.dao.DriverDao;
import ua.com.alevel.persistence.entity.Car;
import ua.com.alevel.persistence.entity.Driver;
import ua.com.alevel.service.CarService;
import ua.com.alevel.service.DriverService;
import ua.com.alevel.view.controller.CarController;
import ua.com.alevel.view.controller.DriverController;
import ua.com.alevel.view.controller.MainController;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
//@Sql(value = "/delete_date_table.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class WebApplicationTest {

    @Autowired
    private CarController carController;

    @Autowired
    private DriverController driverController;

    @Autowired
    private MainController mainController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private CarService carService;

    @Test
    public void aCreateDriver_Car() throws Exception {
        for (int i = 0; i < 5; i++) {
            Driver driver = new Driver();
            driver.setFirstName("dru " + i);
            driver.setLastName("dru" + i);
            driver.setNotes("dru " + i);
            driver.setBalance(100 + i);
            driver.setImageUrl("/http://dru" + i);
            for (int l = 0; l < 2; l++) {
                Car car = new Car();
                car.setCarName(l + "Dru car " + i);
                car.setCarNumber(l + "AA" + i + i + i + i + "AA");
                car.setImageUrl(l + "/http://druCar" + i);
                car.setColor(l + "red " + i);
                car.setYearsOfIssue(l + 2000 + i);
                car.setEngineCapacity(l + 0.1 + l);
                driver.addCar(car);
            }
            driverService.create(driver);
        }
        Assertions.assertEquals(5, driverDao.count());
        Assertions.assertEquals(10, carDao.count());
    }

    @Test
    public void bUpdateDriver() throws Exception {
        for (long i = 1; i <= 5; i++) {
            Driver driver = driverService.findById(i);
            driver.setFirstName("updated" + i);
            driver.setLastName("updated" + i);
            driver.setNotes("updated " + i);
            driver.setBalance(200 + i);
            driver.setImageUrl("/http://updated" + i);
            driverService.update(driver);
        }
        Assertions.assertEquals(5, driverDao.count());
    }

    @Test
    public void cFindCarsByDriverId() throws Exception {
        Map<Long, String> cars = driverService.findCarsByDriverId(1L);
        Assertions.assertEquals(2, cars.size());
    }

    @Test
    public void dFindDriversByCarId() throws Exception {
        Map<Long, String> drivers = carService.findDriversByCarId(1L);
        Assertions.assertEquals(1, drivers.size());
    }

    @Test
    public void eFindAllCarsByDriverId() throws Exception {
        List<Car> cars = driverService.findAllCarsByDriverId(1L);
        Assertions.assertEquals(2, cars.size());
    }

    @Test
    public void fCreateRelation() throws Exception {
        Driver driver = driverService.findById(3L);
        driverService.createRelation(3L, 1L);
        Assertions.assertEquals(3, driver.getCars().size());
    }

    @Test
    public void gDeleteCar() throws Exception {
        for (long i = 1; i <= 5; i++) {
            carService.delete(i);
        }
        Assertions.assertEquals(5, carDao.count());
    }

    @Test
    public void hAddCar() throws Exception {
        Car car = new Car();
        car.setCarName("Dru car ");
        car.setCarNumber("AA1111AA");
        car.setImageUrl("/http://druCar");
        car.setColor("red ");
        car.setYearsOfIssue(2000);
        car.setEngineCapacity(0.1);
        carService.create(car, 4L);
        Assertions.assertEquals(3, driverService.findById(4L).getCars().size());
    }

    @Test
    public void iDeleteDriver() throws Exception {
        for (long i = 1; i <= 5; i++) {
            driverService.delete(i);
        }
        Assertions.assertEquals(0, driverDao.count());
        Assertions.assertEquals(0, carDao.count());
    }

    @Test
    public void jGeneralTest() throws Exception {
        Driver driver = new Driver();
        driver.setFirstName("mike");
        driver.setImageUrl("http://mike");
        driver.setBalance(100);
        driver.setLastName("mike");
        driver.setNotes("mike");
        driverService.create(driver);
        Assertions.assertEquals(1, driverDao.count());
        Car car = new Car();
        car.setImageUrl("http://car");
        car.setCarName("car");
        car.setColor("car");
        car.setCarNumber("CA1111AR");
        car.setYearsOfIssue(2021);
        car.setEngineCapacity(228);
        carService.create(car, 6L);
        driver = driverService.findById(6L);
        Assertions.assertEquals(1, carDao.count());
        Assertions.assertEquals(1, driver.getCars().size());
        driverService.delete(6L);
        Assertions.assertEquals(0, driverDao.count());
        Assertions.assertEquals(0, carDao.count());
    }

    @Test
    @Sql(value = "/data-before.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void kTestAtController() throws Exception {
        assertThat(carController).isNotNull();
        assertThat(driverController).isNotNull();
        assertThat(mainController).isNotNull();
    }

    @Test
    public void lTestDriverController() throws Exception {
        this.mockMvc.perform(get("/drivers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("All Drivers")));
        this.mockMvc.perform(get("/drivers/new"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("New Driver")));
        this.mockMvc.perform(get("/drivers/details/4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Eckel")));
        this.mockMvc.perform(get("/drivers/update/4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Update Driver")));
    }

    @Test
    @Sql(value = "/delete_date_table.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void mTestCarController() throws Exception {
        this.mockMvc.perform(get("/cars"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("All Cars")));
        this.mockMvc.perform(get("/cars/details/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hyinday Elantra")));
        this.mockMvc.perform(get("/cars/update/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Update Car")));
        this.mockMvc.perform(get("/cars/createRelation/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("New Relation")));
    }
}