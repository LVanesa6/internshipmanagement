package com.uptc.bc.internshipmanagement.mapper;

import com.uptc.bc.internshipmanagement.dto.UserDTO;
import com.uptc.bc.internshipmanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role", target = "role")
    UserDTO toDTO(User user);

    @Mapping(source = "role", target = "role")
    User toEntity(UserDTO userDTO);
}
