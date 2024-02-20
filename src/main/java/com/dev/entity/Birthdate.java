package com.dev.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Birthdate(LocalDate birthDate) {

    public long getAge() {
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }
}
