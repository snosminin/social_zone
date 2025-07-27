package name.snosminin.postingservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import name.snosminin.postingservice.dto.CreatePostDto;
import name.snosminin.postingservice.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
@Validated
public class PostController {

    private final PostService postService;


    @PostMapping("/add")
    @Operation(summary = "Add comment to post")
    public ResponseEntity<Boolean> addPost(@Validated @RequestBody @NotNull CreatePostDto request) {
        postService.add(request);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}