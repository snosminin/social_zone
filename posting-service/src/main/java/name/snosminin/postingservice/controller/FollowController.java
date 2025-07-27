package name.snosminin.postingservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import name.snosminin.postingservice.dto.CreateFollowDto;
import name.snosminin.postingservice.service.FollowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/follow")
@RequiredArgsConstructor
@Validated
public class FollowController {

    private final FollowService followService;


    @PostMapping("/add")
    @Operation(summary = "Add comment to post")
    public ResponseEntity<Boolean> addFollow(@Validated @RequestBody @NotNull CreateFollowDto request) {
        followService.add(request);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}