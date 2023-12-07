package ecommerce.spring.keywords;

import java.util.Collection;

import ecommerce.spring.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Keywords {
    @Id
    @GeneratedValue
    private Long id;

    private String keyword;

    @ManyToMany(mappedBy = "keywords")
    private Collection<Product> products;
}
