package ecommerce.spring.auth;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid RegisterRequest request) {
        return new ResponseEntity<AuthenticationResponse>(authenticationService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return new ResponseEntity<AuthenticationResponse>(authenticationService.authenticate(request), HttpStatus.OK);
    }

    @GetMapping
    public Map<String, Object> currentUser(OAuth2AuthenticationToken token) {
        return token.getPrincipal().getAttributes();
    }

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @PostMapping("/google")
    public void gooleAuth(@RequestBody String request) throws GeneralSecurityException, IOException {

        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections
                        .singletonList(clientId))
                .build();
        GoogleIdToken idToken = verifier
                .verify(request);
        Payload payload = idToken.getPayload();
        authenticationService.registerGoogleUser(payload);
    }

    @PostMapping("/googleLogin")
    public ResponseEntity<Boolean> gooleLogin(@RequestBody String request)
            throws GeneralSecurityException, IOException {
        // String token = request.substring(request.indexOf('=') + 1,
        // request.indexOf('&'));
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections
                        .singletonList(clientId))
                .build();
        GoogleIdToken idToken = verifier
                .verify(request);
        Payload payload = idToken.getPayload();
        if (authenticationService.googleLogin(payload))
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}