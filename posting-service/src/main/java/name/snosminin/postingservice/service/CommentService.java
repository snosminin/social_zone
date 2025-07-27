package name.snosminin.postingservice.service;

import name.snosminin.postingservice.dto.CreateCommentDto;

public interface CommentService {

    void add(CreateCommentDto dto);
}
