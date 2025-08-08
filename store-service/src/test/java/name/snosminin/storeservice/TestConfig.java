package name.snosminin.storeservice;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;

@Tag("unitTest")
@TestConfiguration(proxyBeanMethods = false)
public class TestConfig {

    @Test
    void test() {
    }
}
