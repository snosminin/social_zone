package name.snosminin.authservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    @Operation(summary = "Send hello")
    public ResponseEntity<String> send() {
        return new ResponseEntity<>("hello from auth service", HttpStatus.OK);
    }
}