import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppConfigTest {

    @Test
    void testConfigLoad() {
        AppConfig config = new AppConfig();

        String value = config.getValue("app.name");

        assertNotNull(value, "Value for key 'app.name' must not be null");
        assertEquals("MyApp", value, "Incorrect value in config.properties");
    }

    @Test
    void testMissingKey() {
        AppConfig config = new AppConfig();

        assertNull(config.getValue("undefined.key"));
    }
}
