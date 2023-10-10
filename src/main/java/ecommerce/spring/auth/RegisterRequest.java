package ecommerce.spring.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull
    @NotEmpty
    private String firstname;
    @NotNull
    @NotEmpty
    private String lastname;
    @Email
    private String email;
    @NotNull
    @NotEmpty
    private String password;
}
