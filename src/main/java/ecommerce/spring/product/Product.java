package ecommerce.spring.product;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ecommerce.spring.keywords.Keywords;
import ecommerce.spring.store.Store;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "id")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String[] images;
    private Float price;
    private Integer stock;
    private String category;
    private String brand;
    private String[] collection;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "product_keywords", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "keywords_id"))
    private Collection<Keywords> keywords;

    private String visibility;
    private Date pushDate;
    private Integer visitors;
    private Integer sales;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private Store store;
}
