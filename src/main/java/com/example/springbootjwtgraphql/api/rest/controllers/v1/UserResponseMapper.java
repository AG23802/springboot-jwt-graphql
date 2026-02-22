package com.example.springbootjwtgraphql.api.rest.controllers.v1;

import com.example.springbootjwtgraphql.api.rest.dto.UserResponse;
import com.example.springbootjwtgraphql.domain.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    UserResponse toUserResponse(User user);
}
