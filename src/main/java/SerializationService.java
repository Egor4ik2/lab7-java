import java.io.File;

public interface SerializationService<T> {
    void write(CollectionWrapper<T> wrapper, File file) throws DataSerializationException;
    CollectionWrapper<T> read(File file, Class<T> itemClass) throws DataSerializationException;
}
