package ecommerce.spring.follow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    public Follow newFollow(Follow follow) {
        Follow res = followRepository.save(follow);
        return res;
    }
}
