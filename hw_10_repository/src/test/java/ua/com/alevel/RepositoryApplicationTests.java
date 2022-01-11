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
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Car;
import ua.com.alevel.persistence.entity.Driver;
import ua.com.alevel.persistence.repository.repository.CarRepository;
import ua.com.alevel.persistence.repository.repository.DriverRepository;
import ua.com.alevel.service.CarService;
import ua.com.alevel.service.DriverService;
import ua.com.alevel.view.controller.CarController;
import ua.com.alevel.view.controller.DriverController;
import ua.com.alevel.view.controller.MainController;

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

public class RepositoryApplicationTests {

    @Autowired
    private DriverService driverService;

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CarController carController;

    @Autowired
    private DriverController driverController;

    @Autowired
    private MainController mainController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void aCreateDriver() {
        for (int i = 0; i < 10; i++) {
            Driver driver = new Driver();
            driver.setFirstName("dru " + i);
            driver.setLastName("dru" + i);
            driver.setNotes("dru " + i);
            driver.setBalance(100 + i);
            driver.setImageUrl("/http://dru" + i);
            driverService.create(driver);
        }
        Assertions.assertEquals(10, driverRepository.countDrivers());
    }

    @Test
    public void bCreateNotLinkCars() {
        for (int i = 0; i < 5; i++) {
            Car car = new Car();
            car.setCarName("Dru car " + i);
            car.setCarNumber("AA1111AA" + i);
            car.setImageUrl("/http://druCar" + i);
            car.setColor("red " + i);
            car.setYearsOfIssue(2000 + i);
            car.setEngineCapacity(0.1 + i);
            carService.create(car, 0L);
        }
        Assertions.assertEquals(5, carRepository.countCars());
    }

    @Test
    public void cCreateLinkCars() {
        for (int i = 5; i < 10; i++) {
            Car car = new Car();
            car.setCarName("Dru car " + i);
            car.setCarNumber("AA1111AA" + i);
            car.setImageUrl("/http://druCar" + i);
            car.setColor("red " + i);
            car.setYearsOfIssue(2000 + i);
            car.setEngineCapacity(0.1 + i);
            carService.create(car, (long) i - 4);
        }
        Assertions.assertEquals(1, driverService.findAllCarsByDriverId(1L).size());
        Assertions.assertEquals(10, carRepository.countCars());
    }

    @Test
    public void dFindAllCars() {
        DataTableRequest request = new DataTableRequest();
        request.setSort("id");
        request.setOrder("desc");
        request.setPage(1);
        request.setSize(10);

        DataTableResponse<Car> cars = carService.findAll(request);

        System.out.println("CARS");
        for (int i = 0; i < cars.getItemsSize(); i++) {
            System.out.println("cars = " + cars.getItems().get(i));
        }

        Assertions.assertEquals(4L, cars.getItems().get(6).getId());
        Assertions.assertEquals(10, cars.getItemsSize());
    }

    @Test
    public void eFindAllDrivers() {
        DataTableRequest request = new DataTableRequest();
        request.setSort("id");
        request.setOrder("desc");
        request.setPage(1);
        request.setSize(10);

        DataTableResponse<Driver> drivers = driverService.findAll(request);

        System.out.println("DRIVERS");
        for (int i = 0; i < drivers.getItemsSize(); i++) {
            System.out.println("drivers = " + drivers.getItems().get(i));
        }

        Assertions.assertEquals(4L, drivers.getItems().get(6).getId());
        Assertions.assertEquals(10, drivers.getItemsSize());
    }

    @Test
    public void fUpdateDriver() {
        for (int i = 1; i <= 5; i++) {
            Driver driver = driverService.findById((long) i).get();
            driver.setFirstName("dru " + i + " updated");
            driver.setLastName("dru" + i + " updated");
            driver.setNotes("dru " + i + " updated");
            driver.setBalance(100 + i);
            driver.setImageUrl("/http://dru" + i + " updated");
            driverService.update(driver);
        }
        Assertions.assertEquals(10, driverRepository.countDrivers());
    }

    @Test
    public void gDeleteCar() {
        for(long i = 1; i <= 5; i++){
            carService.delete(i);
        }
        Assertions.assertEquals(10, driverRepository.countDrivers());
        Assertions.assertEquals(5, carRepository.countCars());
    }

    @Test
    public void hDeleteDriver() {
        for(long i = 1; i <= 10; i++){
            driverService.delete(i);
        }
        Assertions.assertEquals(0, driverRepository.countDrivers());
        Assertions.assertEquals(0, carRepository.countCars());
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
