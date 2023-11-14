package ecommerce.spring.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.spring.dtos.NewProductDto;
import ecommerce.spring.store.Store;
import ecommerce.spring.store.StoreRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    public Product addProduct(NewProductDto data) {
        Store store = storeRepository.findByEmail(data.getStoreEmail()).orNull();
        Product product = data.getProduct();
        product.setStore(store);
        return productRepository.save(product);
    }

    public List<Product> userProducts(Long id) {
        List<Product> products = productRepository.findByStoreSellerId(id);
        return products;
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }

    public Product updateProduct(Product product) {
        Product old = productRepository.findById(product.getId()).orElse(product);
        product.setId(old.getId());
        product.setStore(old.getStore());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
