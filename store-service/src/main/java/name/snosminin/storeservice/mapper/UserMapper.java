package name.snosminin.storeservice.mapper;

import name.snosminin.storeservice.dto.CreateUserDto;
import name.snosminin.storeservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    CreateUserDto toDto(User entity);

    User toEntity(CreateUserDto dto);
}