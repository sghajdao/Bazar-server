package ecommerce.spring.follow;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Collection<Follow> findByStoreId(Long storeId);
}
