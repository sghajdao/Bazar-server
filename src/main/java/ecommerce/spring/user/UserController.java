package ecommerce.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.spring.dtos.UserResponseDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/email")
    public ResponseEntity<UserResponseDto> getUserByEmail(@RequestBody String email) {
        User user = userService.getUserByEmail(email);
        UserResponseDto dto = new UserResponseDto("user", user);
        if (user != null)
            return new ResponseEntity<UserResponseDto>(dto, HttpStatus.OK);
        return new ResponseEntity<UserResponseDto>(dto, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/id")
    public ResponseEntity<User> getUserById(@RequestBody Long id) {
        User user = userService.getUserById(id);
        if (user != null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
