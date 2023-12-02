package ecommerce.spring.dtos;

import ecommerce.spring.store.Store;
import ecommerce.spring.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponse {
    Store store;
    User seller;
}
