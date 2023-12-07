package ecommerce.spring.product;

import org.springframework.stereotype.Repository;

import ecommerce.spring.keywords.Keywords;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStoreSellerId(Long sellerId);

    // @Query(value = "SELECT * FROM product WHERE LOWER(array_to_string(keywords,
    // ',')) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    // List<Product> findByKeyword(@Param("keyword") String keyword);

    // @Query("SELECT DISTINCT p FROM Product p JOIN p.keywords k WHERE
    // LOWER(k.keyword) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> findByKeywords(Keywords keyword);
}
