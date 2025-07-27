package name.snosminin.authservice.controller;


import lombok.RequiredArgsConstructor;
import name.snosminin.authservice.dto.AuthRequest;
import name.snosminin.authservice.dto.AuthResponse;
import name.snosminin.authservice.dto.SignUpRequest;
import name.snosminin.authservice.service.AuthService;
import name.snosminin.authservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody SignUpRequest signUpRequest) {

        var user = userService.create(signUpRequest);
        return new ResponseEntity<>(String.format("%s has been created", user.getUsername()), HttpStatus.CREATED);
    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest authRequest) {

        var authResponse = authService.login(authRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
