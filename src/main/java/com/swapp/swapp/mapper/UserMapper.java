package com.swapp.swapp.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.swapp.swapp.dto.request.UserRequestDTO;
import com.swapp.swapp.dto.response.UserBasicResponseDTO;
import com.swapp.swapp.dto.response.UserProfileResponseDTO;
import com.swapp.swapp.entity.User;

@Mapper(componentModel ="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User toEntity (UserRequestDTO dto, byte[] picture);

    UserBasicResponseDTO toBasicResponse (User user);

    UserProfileResponseDTO toProfileResponse (User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UserRequestDTO dto, @MappingTarget User user);

}
