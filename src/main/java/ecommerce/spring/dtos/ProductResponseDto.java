package ecommerce.spring.dtos;

import ecommerce.spring.product.Product;
import ecommerce.spring.store.Store;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDto {
    Product product;
    Store store;
}