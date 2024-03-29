package ecommerce.spring.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.spring.dtos.NewStoreRequestDto;
import ecommerce.spring.dtos.NewStoreResponseDto;
import ecommerce.spring.dtos.StoreResponse;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreService storesService;

    @PostMapping("/new")
    public ResponseEntity<NewStoreResponseDto> newStore(@RequestBody NewStoreRequestDto store) {
        NewStoreResponseDto data = storesService.addStore(store);
        if (data.getMessage() == "Success saving")
            return new ResponseEntity<NewStoreResponseDto>(data, HttpStatus.CREATED);
        return new ResponseEntity<NewStoreResponseDto>(data, HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/get/selleremail")
    public ResponseEntity<StoreResponse> getStore(@RequestBody String userEmail) {
        Store store = storesService.getStoreByUserEmail(userEmail);
        if (store != null) {
            StoreResponse res = new StoreResponse(store, store.getSeller());
            return new ResponseEntity<StoreResponse>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<Store> updateStore(@RequestBody Store store) {
        Store updated = storesService.updateStore(store);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StoreResponse> getStoreById(@PathVariable Long id) {
        Store store = storesService.getStoreById(id);
        if (store != null) {
            StoreResponse res = new StoreResponse(store, store.getSeller());
            return new ResponseEntity<StoreResponse>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
