package name.snosminin.storeservice.mapper;

import name.snosminin.storeservice.dto.CreateCommentDto;
import name.snosminin.storeservice.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CreateCommentDto toDto(Comment entity);

    Comment toEntity(CreateCommentDto dto);
}