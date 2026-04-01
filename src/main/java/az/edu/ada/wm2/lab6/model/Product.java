package az.edu.ada.wm2.lab6.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;
import az.edu.ada.wm2.lab6.model.Category;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Entity
public class Product {

    @Id
    private UUID id;
    private String productName;
    private BigDecimal price;
    private LocalDate expirationDate;

    // Constructors
    public Product() {
    }

    public Product(String productName, BigDecimal price, LocalDate expirationDate) {
        this.id = UUID.randomUUID();
        this.productName = productName;
        this.price = price;
        this.expirationDate = expirationDate;
    }

    public Product(UUID id, String productName, BigDecimal price, LocalDate expirationDate) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.expirationDate = expirationDate;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", expirationDate=" + expirationDate +
                '}';
    }

    @ManyToMany
@JoinTable(
    name = "product_category",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id")
)
private Set<Category> categories1 = new HashSet<>();


public Set<Category> getCategories() {
    return categories1;
}

public void setCategories(List<Category> categories2) {
    this.categories1 = (Set<Category>) categories2;
}

@ManyToMany
@JoinTable(
    name = "product_category",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id")
)
private List<Category> categories11 = new ArrayList<>();

public void setCategories1(List<Category> categories2) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setCategories'");
}

private List<Category> categories = new ArrayList<>();
}

