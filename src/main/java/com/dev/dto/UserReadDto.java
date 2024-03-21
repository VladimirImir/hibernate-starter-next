package com.dev.dto;

import com.dev.entity.PersonalInfo;
import com.dev.entity.Role;

public record UserReadDto(Long id,
                          PersonalInfo personalInfo,
                          String username,
                          Role role,
                          CompanyReadDto company) {
}
