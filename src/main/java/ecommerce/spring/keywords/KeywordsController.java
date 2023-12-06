package ecommerce.spring.keywords;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/keywords")
public class KeywordsController {
    @Autowired
    private KeywordsService keywordsService;

    @GetMapping("/search/{query}")
    public ResponseEntity<Collection<Keywords>> searchQuery(@PathVariable String query) {
        Collection<Keywords> keywords = keywordsService.searchProducts(query);
        return new ResponseEntity<>(keywords, HttpStatus.OK);
    }
}
