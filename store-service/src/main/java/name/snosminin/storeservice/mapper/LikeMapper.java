package name.snosminin.storeservice.mapper;

import name.snosminin.storeservice.dto.CreateLikeDto;
import name.snosminin.storeservice.model.Like;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    CreateLikeDto toDto(Like entity);

    Like toEntity(CreateLikeDto dto);
}