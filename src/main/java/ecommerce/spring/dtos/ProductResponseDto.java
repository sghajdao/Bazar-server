package ecommerce.spring.dtos;

import ecommerce.spring.product.Product;
import ecommerce.spring.store.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    Product product;
    Store store;
}