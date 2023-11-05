package ecommerce.spring.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.spring.dtos.NewProductDto;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/new")
    public ResponseEntity<Boolean> newProduct(@RequestBody NewProductDto data) {
        System.out.println(data);
        productService.addProduct(data);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Product>> getUserProducts(@PathVariable Long id) {
        return new ResponseEntity<>(productService.userProducts(id), HttpStatus.OK);
    }
}
