package ecommerce.spring.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.spring.dtos.NewProductDto;
import ecommerce.spring.user.User;
import ecommerce.spring.user.UserRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Product addProduct(NewProductDto data) {
        User user = userRepository.findByEmail(data.getEmail()).orElse(null);
        Product product = data.getProduct();
        product.setSeller(user);
        return productRepository.save(product);
    }

    public List<Product> userProducts(Long id) {
        return productRepository.findBySellerId(id);
    }
}
