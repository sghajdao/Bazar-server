package ecommerce.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestBody String email) {
        return new ResponseEntity<User>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @PostMapping("/welcome")
    public ResponseEntity<User> welcome(@RequestBody String email) {
        System.out.println(email);
        return new ResponseEntity<User>(userService.welcome(email), HttpStatus.OK);
    }
}