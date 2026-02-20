package com.example.springbootjwtgraphql.api.rest.controllers.v1;

import com.example.springbootjwtgraphql.api.rest.dto.RegisterRequest;
import com.example.springbootjwtgraphql.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", expression = "java(request.email().split(\"@\")[0])")
    User toEntity(RegisterRequest request);
}