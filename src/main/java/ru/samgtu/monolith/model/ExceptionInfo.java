package ru.samgtu.monolith.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Creation date: 09.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Data
public class ExceptionInfo {
    private String message;

    private LocalDateTime timestamp;
}
