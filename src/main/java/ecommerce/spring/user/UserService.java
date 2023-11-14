package ecommerce.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.spring.store.Store;
import ecommerce.spring.store.StoreRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    public User getUserByEmail(String email) {
        // Store store = storeRepository.findBySellerEmail(email).orNull();
        User user = userRepository.findByEmail(email).orElse(null);
        // if (user != null && store != null)
        // user.setStore(store);
        return user;
    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }
}
