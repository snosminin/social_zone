package name.snosminin.storeservice.mapper;

import name.snosminin.storeservice.dto.CreateFollowDto;
import name.snosminin.storeservice.model.Follow;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FollowMapper {

    CreateFollowDto toDto(Follow entity);

    Follow toEntity(CreateFollowDto dto);
}