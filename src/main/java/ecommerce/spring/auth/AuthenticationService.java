package ecommerce.spring.auth;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ecommerce.spring.config.JwtService;
import ecommerce.spring.error.NotFoundException;
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
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(jwtToken).build();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException(
                    String.format("No such user whith email [%s] was found in database", request.getEmail()));
        }
    }

}