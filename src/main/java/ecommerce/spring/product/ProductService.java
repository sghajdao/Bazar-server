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
        // Store store = storeRepository.findByEmail(data.getStoreEmail()).orNull();
        Product product = data.getProduct();
        // product.setStore(store);
        return productRepository.save(product);
    }

    public List<Product> userProducts(Long id) {
        List<Product> products = productRepository.findByStoreSellerId(id);
        System.out.println(products);
        return products;
    }
}
