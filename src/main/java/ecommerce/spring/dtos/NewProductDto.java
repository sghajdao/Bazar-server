package ecommerce.spring.dtos;

import java.util.Collection;

import ecommerce.spring.product.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewProductDto {
    Product product;
    Collection<String> keywords;
    String storeEmail;
}
