package ecommerce.spring.dtos;

import ecommerce.spring.store.Store;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewStoreRequestDto {
    Store store;
    String sellerEmail;
}
