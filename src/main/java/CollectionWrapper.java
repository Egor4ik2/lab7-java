import java.util.List;

public class CollectionWrapper<T> {
    private List<T> items;

    public CollectionWrapper() {}

    public CollectionWrapper(List<T> items) { this.items = items; }

    public List<T> getItems() { return items; }
    public void setItems(List<T> items) { this.items = items; }
}
