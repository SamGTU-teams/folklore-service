package ru.samgtu.monolith.activity.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.parse;

@Component
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
    private final static String PATTERN = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(:\\d{2})?";

    @Override
    public LocalDateTime convert(String s) {
        return s.matches(PATTERN) ? parse(s) : null;
    }
}
