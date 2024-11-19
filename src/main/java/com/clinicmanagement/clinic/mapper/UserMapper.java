package com.clinicmanagement.clinic.mapper;

import com.clinicmanagement.clinic.Entities.Useracount;
import com.clinicmanagement.clinic.dto.user.UserReponse;
import com.clinicmanagement.clinic.dto.user.UserRequest;
import com.clinicmanagement.clinic.dto.user.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "doctor", source = "doctor")
    @Mapping(target = "patient", source = "patient")
    Useracount toUser(UserRequest userRegisterRes);

    UserReponse toUserReponse(Useracount user);

    void updateUser(UserUpdateRequest userUpdateRequest, @MappingTarget Useracount user);
}
