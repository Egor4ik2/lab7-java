import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class JacksonSerializationService<T> implements SerializationService<T> {

    private final ObjectMapper jsonMapper;
    private final ObjectMapper yamlMapper;

    public JacksonSerializationService() {
        this.jsonMapper = new ObjectMapper();
        this.yamlMapper = new ObjectMapper(new YAMLFactory());
    }

    @Override
    public void write(CollectionWrapper<T> wrapper, File file) throws DataSerializationException {
        try {
            if (file.getName().endsWith(".yaml") || file.getName().endsWith(".yml")) {
                yamlMapper.writerWithDefaultPrettyPrinter().writeValue(file, wrapper);
            } else {
                jsonMapper.writerWithDefaultPrettyPrinter().writeValue(file, wrapper);
            }
        } catch (IOException e) {
            throw new DataSerializationException("Failed to write to file: " + file.getPath(), e);
        }
    }

    @Override
    public CollectionWrapper<T> read(File file, Class<T> itemClass) throws DataSerializationException {
        try {
            if (!file.exists()) throw new DataSerializationException("File not found: " + file.getPath());
            if (file.getName().endsWith(".yaml") || file.getName().endsWith(".yml")) {
                return yamlMapper.readValue(file, yamlMapper.getTypeFactory().constructParametricType(CollectionWrapper.class, itemClass));
            } else {
                return jsonMapper.readValue(file, jsonMapper.getTypeFactory().constructParametricType(CollectionWrapper.class, itemClass));
            }
        } catch (IOException e) {
            throw new DataSerializationException("Failed to read from file: " + file.getPath(), e);
        }
    }
}
