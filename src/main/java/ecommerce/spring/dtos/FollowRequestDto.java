package ecommerce.spring.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FollowRequestDto {
    Long me;
    Long followed;
}
