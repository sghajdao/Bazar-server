package ecommerce.spring.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.spring.dtos.NewStoreRequestDto;
import ecommerce.spring.dtos.NewStoreResponseDto;
import ecommerce.spring.user.User;
import ecommerce.spring.user.UserRepository;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    public NewStoreResponseDto addStore(NewStoreRequestDto store) {
        Store exist = storeRepository.findByEmail(store.getStore().getEmail()).orNull();
        if (exist != null) {
            return NewStoreResponseDto.builder().message("Email already exist").store(exist).build();
        }
        User seller = userRepository.findByEmail(store.getSellerEmail()).orElse(null);
        Store newStore = store.getStore();
        newStore.setSeller(seller);
        storeRepository.save(newStore);
        return NewStoreResponseDto.builder().message("Success saving").store(newStore).build();
    }
}
