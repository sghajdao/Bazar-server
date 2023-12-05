package ecommerce.spring.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.spring.dtos.NewProductDto;
import ecommerce.spring.dtos.ProductResponseDto;
import ecommerce.spring.user.User;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/new")
    public ResponseEntity<Boolean> newProduct(@RequestBody NewProductDto data) {
        productService.addProduct(data);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<Product>> getUserProducts(@PathVariable Long id) {
        List<Product> products = productService.userProducts(id);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getProductById(@PathVariable Long id) {
        User seller = productService.getUserByProductId(id);
        if (seller != null)
            return new ResponseEntity<User>(seller, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_EXTENDED);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<Collection<ProductResponseDto>> searchQuery(@PathVariable String query) {
        Collection<Product> products = productService.getProductsByKeyword(query);
        Collection<ProductResponseDto> res = new ArrayList<>();
        products.forEach(prod -> res.add(new ProductResponseDto(prod, prod.getStore())));
        return new ResponseEntity<Collection<ProductResponseDto>>(res, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product newProduct = productService.updateProduct(product);
        return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
