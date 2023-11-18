package ecommerce.spring.dtos;

import java.util.Collection;

import ecommerce.spring.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowRsponseDto {
    Collection<User> users;
}
