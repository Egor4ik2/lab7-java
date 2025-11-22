import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            AppConfig cfg = new AppConfig();

            SerializationService<Product> ser = new JacksonSerializationService<>();

            ProductRepository repo = new ProductRepository();
            int n = cfg.getTestItemsCount();

            IntStream.range(0, n).forEach(i -> {
                Product p = new Product(
                        "p" + i,
                        "Product-" + i,
                        Math.round((10 + Math.random() * 90) * 100.0) / 100.0,
                        (i + 1) * 2
                );
                repo.add(p);
            });

            logger.info("Created repository with {} items.", repo.size());

            // Шляхи беруться з config.properties
            File jsonFile = new File(cfg.getJsonPath());
            File yamlFile = new File(cfg.getYamlPath());

            ensureParents(jsonFile);
            ensureParents(yamlFile);

            CollectionWrapper<Product> wrapper = new CollectionWrapper<>(repo.getAll());
            ser.write(wrapper, jsonFile);
            ser.write(wrapper, yamlFile);

            logger.info("Saved to {} and {}.", jsonFile.getPath(), yamlFile.getPath());

            CollectionWrapper<Product> readJson = ser.read(jsonFile, Product.class);
            CollectionWrapper<Product> readYaml = ser.read(yamlFile, Product.class);

            List<Product> fromJson = readJson.getItems();
            List<Product> fromYaml = readYaml.getItems();

            logger.info("Loaded {} items from JSON, {} items from YAML", fromJson.size(), fromYaml.size());

            boolean eqJson = repo.getAll().equals(fromJson);
            boolean eqYaml = repo.getAll().equals(fromYaml);

            logger.info("Original equals JSON? {}", eqJson);
            logger.info("Original equals YAML? {}", eqYaml);

        } catch (DataSerializationException dse) {
            logger.error("Serialization error: {}", dse.getMessage(), dse);
        } catch (Exception ex) {
            logger.error("Unexpected error: {}", ex.getMessage(), ex);
        }
    }

    private static void ensureParents(File f) throws Exception {
        Path parent = f.toPath().getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
    }
}
