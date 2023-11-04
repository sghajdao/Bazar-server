package ecommerce.spring.dtos;

import ecommerce.spring.product.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewProductDto {
    Product product;
    String email;
}
