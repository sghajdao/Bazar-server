package ecommerce.spring.store;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import ecommerce.spring.follow.Follow;
import ecommerce.spring.product.Product;
import ecommerce.spring.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "_user_id")
    private User seller;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private Collection<Product> product;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private Collection<Follow> followers;
}
