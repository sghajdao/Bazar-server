package ecommerce.spring.keywords;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordsRepository extends JpaRepository<Keywords, Long> {
    List<Keywords> findByKeywordContainingIgnoreCase(String query);

    Optional<Keywords> findByKeyword(String keyword);
}
