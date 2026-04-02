package az.edu.ada.wm2.lab6.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Builder.Default
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    public void setProducts(List<Product> of) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setProducts'");
    }
}