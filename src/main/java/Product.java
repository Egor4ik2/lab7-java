import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;

    public Product() { }

    public Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @JsonProperty("id")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @JsonProperty("name")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @JsonProperty("price")
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @JsonProperty("quantity")
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        return Double.compare(p.price, price) == 0 &&
               quantity == p.quantity &&
               java.util.Objects.equals(id, p.id) &&
               java.util.Objects.equals(name, p.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name, price, quantity);
    }

    @Override
    public String toString() {
        return "Product{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", quantity=" + quantity +
               '}';
    }
}
