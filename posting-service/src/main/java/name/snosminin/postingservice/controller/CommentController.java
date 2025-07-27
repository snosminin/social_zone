package name.snosminin.postingservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import name.snosminin.postingservice.dto.CreateCommentDto;
import name.snosminin.postingservice.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
@Validated
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/add")
    @Operation(summary = "Add comment to post")
    public ResponseEntity<Boolean> addComment(@Validated @RequestBody @NotNull CreateCommentDto request) {
        commentService.add(request);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}