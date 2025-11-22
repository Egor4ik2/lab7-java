import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private final Properties props = new Properties();

    public AppConfig() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new RuntimeException("config.properties not found in resources");
            }
            props.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Cannot load config.properties", e);
        }
    }

    public String getValue(String key) {
        return props.getProperty(key);
    }

    public String getJsonPath() {
        return props.getProperty("json.file");
    }

    public String getYamlPath() {
        return props.getProperty("yaml.file");
    }

    public int getTestItemsCount() {
        return Integer.parseInt(props.getProperty("test.items.count", "5"));
    }
}
