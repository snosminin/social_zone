package name.snosminin.storeservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class StoreServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}
