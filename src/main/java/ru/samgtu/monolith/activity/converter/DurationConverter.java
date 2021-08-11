package ru.samgtu.monolith.activity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Creation date: 10.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {
    @Override
    public Long convertToDatabaseColumn(Duration attribute) {
        return attribute.toMinutes();
    }

    @Override
    public Duration convertToEntityAttribute(Long dbData) {
        return Duration.of(dbData, ChronoUnit.MINUTES);
    }
}
