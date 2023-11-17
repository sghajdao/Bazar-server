package ecommerce.spring.follow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping("/new")
    public ResponseEntity<Follow> newFollow(@RequestBody Follow follow) {
        Follow newFollow = followService.newFollow(follow);
        return new ResponseEntity<Follow>(newFollow, HttpStatus.OK);
    }
}
