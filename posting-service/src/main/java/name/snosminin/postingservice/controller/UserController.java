package name.snosminin.postingservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import name.snosminin.postingservice.dto.CreateUserDto;
import name.snosminin.postingservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;


    @PostMapping("/add")
    @Operation(summary = "Add user to post")
    public ResponseEntity<Boolean> addUser(@Validated @RequestBody @NotNull CreateUserDto request) {
        userService.add(request);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}