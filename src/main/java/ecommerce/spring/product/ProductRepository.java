package ecommerce.spring.product;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStoreSellerId(Long sellerId);

    List<Product> findByTitleContainingIgnoreCase(String query);
}
