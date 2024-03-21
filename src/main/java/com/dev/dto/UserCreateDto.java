package com.dev.dto;

import com.dev.entity.PersonalInfo;
import com.dev.entity.Role;
import com.dev.validation.UpdateCheck;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record UserCreateDto(
        @Valid
        PersonalInfo personalInfo,
        @NotNull
        String username,
        @NotNull(groups = UpdateCheck.class)
        Role role,
        Integer companyId) {
}
