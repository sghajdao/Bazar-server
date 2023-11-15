package ecommerce.spring.product;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ecommerce.spring.store.Store;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    private String[] keywords;
    private String visibility;
    private Date pushDate;
    private Integer visitors;
    private Integer sales;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "store_id")
    private Store store;
}
