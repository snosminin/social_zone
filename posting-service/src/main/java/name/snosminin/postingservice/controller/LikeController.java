package name.snosminin.postingservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import name.snosminin.postingservice.dto.CreateLikeDto;
import name.snosminin.postingservice.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/like")
@RequiredArgsConstructor
@Validated
public class LikeController {

    private final LikeService likeService;


    @PostMapping("/add")
    @Operation(summary = "Add like")
    public ResponseEntity<Boolean> addLike(@Validated @RequestBody @NotNull CreateLikeDto request) {
        likeService.add(request);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}