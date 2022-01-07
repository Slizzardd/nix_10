package ua.com.alevel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.persistence.dao.DriverDao;

@SpringBootTest
class WebJpaApplicationTests {

    @Autowired
    DriverDao driverDao;

    @Test
    void contextLoads() {
    }

}
