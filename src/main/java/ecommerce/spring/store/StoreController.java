package ecommerce.spring.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.spring.dtos.NewStoreRequestDto;
import ecommerce.spring.dtos.NewStoreResponseDto;

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
}
