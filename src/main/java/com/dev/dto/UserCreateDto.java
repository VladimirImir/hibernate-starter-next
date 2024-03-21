package com.dev.dto;

import com.dev.entity.PersonalInfo;
import com.dev.entity.Role;

public record UserCreateDto(PersonalInfo personalInfo,
                            String username,
                            Role role,
                            Integer companyId) {
}
