import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepository {
    private final List<Product> items = new ArrayList<>();

    public synchronized void add(Product p) {
        if (p == null) throw new RepositoryException("Cannot add null product");
        items.add(p);
    }

    public synchronized List<Product> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(items));
    }

    public synchronized void clear() {
        items.clear();
    }

    public synchronized int size() {
        return items.size();
    }
}
