package ecommerce.spring.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.spring.dtos.NewProductDto;
import ecommerce.spring.keywords.Keywords;
import ecommerce.spring.keywords.KeywordsRepository;
import ecommerce.spring.store.Store;
import ecommerce.spring.store.StoreRepository;
import ecommerce.spring.user.User;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private KeywordsRepository keywordsRepository;

    public Product addProduct(NewProductDto data) {
        Store store = storeRepository.findByEmail(data.getStoreEmail()).orNull();
        Product product = data.getProduct();
        product.setStore(store);

        Collection<Keywords> keywords = new ArrayList<>();
        for (String keywordString : data.getKeywords()) {
            Keywords keyword = keywordsRepository.findByKeyword(keywordString)
                    .orElseGet(() -> {
                        Keywords newKeyword = new Keywords();
                        newKeyword.setKeyword(keywordString);
                        return keywordsRepository.save(newKeyword);
                    });
            keywords.add(keyword);
        }
        // product.setKeywords(keywords);
        return productRepository.save(product);
    }

    public List<Product> userProducts(Long id) {
        List<Product> products = productRepository.findByStoreSellerId(id);
        return products;
    }

    public User getUserByProductId(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product.getStore().getSeller();
    }

    public Product updateProduct(Product product) {
        Product old = productRepository.findById(product.getId()).orElse(product);
        product.setId(old.getId());
        product.setStore(old.getStore());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null)
            productRepository.delete(product);
    }

    public List<Product> getProductsByKeyword(Keywords keyword) {
        System.out.println(keyword);
        return productRepository.findByKeywords(keyword.getKeyword());
    }
}
