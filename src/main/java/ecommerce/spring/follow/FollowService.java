package ecommerce.spring.follow;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.spring.dtos.FollowRequestDto;
import ecommerce.spring.dtos.FollowRsponseDto;
import ecommerce.spring.user.User;
import ecommerce.spring.user.UserRepository;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    public Follow newFollow(FollowRequestDto follow) {
        User follower = userRepository.findById(follow.getMe()).orElse(null);
        User followed = userRepository.findById(follow.getFollowed()).orElse(null);
        if (followed != null && follower != null) {
            Follow req = Follow.builder()._user(follower).store(followed.getStore()).build();

            Follow res = followRepository.save(req);
            return res;
        }
        return null;
    }

    public FollowRsponseDto getFollowersByStoreId(Long id) {
        Collection<Follow> follows = followRepository.findByStoreId(id);

        // Extract users from the follows
        Collection<User> users = follows.stream()
                .map(Follow::get_user)
                .collect(Collectors.toList());
        return FollowRsponseDto.builder().users(users).build();
    }
}
