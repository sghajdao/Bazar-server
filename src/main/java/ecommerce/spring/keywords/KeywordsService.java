package ecommerce.spring.keywords;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeywordsService {
    @Autowired
    private KeywordsRepository keywordsRepository;

    public Collection<Keywords> searchProducts(String query) {
        return keywordsRepository.findByKeywordContainingIgnoreCase(query);
    }
}
