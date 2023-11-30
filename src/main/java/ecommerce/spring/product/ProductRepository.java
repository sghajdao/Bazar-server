package ecommerce.spring.product;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStoreSellerId(Long sellerId);

    List<Product> findByTitleContainingIgnoreCase(String query);

    // @Query("SELECT p FROM Product p WHERE LOWER(p.keywords) LIKE
    // LOWER(CONCAT('%', :keyword, '%'))")
    @Query(value = "SELECT * FROM product WHERE LOWER(array_to_string(keywords, ',')) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Product> findByKeyword(@Param("keyword") String keyword);
}
