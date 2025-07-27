package name.snosminin.storeservice.mapper;

import name.snosminin.storeservice.dto.CreatePostDto;
import name.snosminin.storeservice.model.Post;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PostMapper {

    CreatePostDto toDto(Post entity);

    Post toEntity(CreatePostDto dto);
}