package com.wonderSoft.bidding.utils;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * ==============================================================
 * Converter implementation for LocalDateTime
 * ==============================================================
 **/
@javax.persistence.Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute == null ? null : Timestamp.valueOf(attribute);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbDate) {
        return dbDate == null ? null : dbDate.toLocalDateTime();
    }
}
