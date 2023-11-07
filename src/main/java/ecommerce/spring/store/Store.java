package ecommerce.spring.store;

import java.util.HashSet;
import java.util.Set;

import ecommerce.spring.product.Product;
import ecommerce.spring.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue
    Long id;

    String image;
    String name;
    String subtitle;
    String email;
    String country;
    String phone;

    @OneToOne
    @JoinColumn(name = "_user_id")
    private User seller;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> product = new HashSet<>();
}
