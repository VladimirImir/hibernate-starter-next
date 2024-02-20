package com.dev.converter;

import com.dev.entity.Birthdate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.util.Optional;

@Converter(autoApply = true)
public class BirthdateConverter implements AttributeConverter<Birthdate, Date> {


    @Override
    public Date convertToDatabaseColumn(Birthdate attribute) {
        return Optional.ofNullable(attribute)
                .map(Birthdate::birthDate)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public Birthdate convertToEntityAttribute(Date dbData) {
        return Optional.ofNullable(dbData)
                .map(Date::toLocalDate)
                .map(Birthdate::new)
                .orElse(null);
    }
}
