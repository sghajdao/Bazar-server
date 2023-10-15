package ecommerce.spring.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import ecommerce.spring.config.JwtService;
import ecommerce.spring.user.Role;
import ecommerce.spring.user.User;
import ecommerce.spring.user.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if (user != null) {
            String password = request.getPassword();
            String encodedPassword = user.getPassword();
            Boolean isPwRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwRight) {
                User user2 = userRepository.findByEmailAndPassword(request.getEmail(), encodedPassword).orElse(null);
                if (user2 != null) {
                    var jwtToken = jwtService.generateToken(user2);
                    return AuthenticationResponse.builder().token(jwtToken).message("Login Success").build();
                } else
                    return AuthenticationResponse.builder().token("").message("Login Field").build();
            } else {
                return AuthenticationResponse.builder().token("").message("Password is incorrect").build();
            }
        } else {
            return AuthenticationResponse.builder().token("").message("Email not exist").build();
        }
    }

    public void registerGoogleUser(Payload payload) {
        var user = User.builder()
                .firstname((String) payload.getOrDefault("given_name", null))
                .lastname((String) payload.getOrDefault("family_name", null))
                .email((String) payload.getOrDefault("email", null))
                .password(null)
                .role(Role.USER)
                .build();
        userRepository.save(user);
    }
}