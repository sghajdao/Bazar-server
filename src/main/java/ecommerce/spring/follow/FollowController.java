package ecommerce.spring.follow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.spring.dtos.FollowRequestDto;
import ecommerce.spring.dtos.FollowRsponseDto;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping("/new")
    public ResponseEntity<Follow> newFollow(@RequestBody FollowRequestDto follow) {
        Follow newFollow = followService.newFollow(follow);
        if (newFollow != null) {
            System.out.println(newFollow);
            return new ResponseEntity<Follow>(newFollow, HttpStatus.OK);
        }
        return new ResponseEntity<Follow>(newFollow, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FollowRsponseDto> getFollowersByStoreId(@PathVariable Long id) {
        FollowRsponseDto res = followService.getFollowersByStoreId(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
