package ru.samgtu.monolith.folklore.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.samgtu.monolith.folklore.exception.NoSuchBuildingException;
import ru.samgtu.monolith.model.ExceptionInfo;

import java.time.LocalDateTime;

/**
 * Creation date: 09.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RestControllerAdvice
public class FolkloreControllerHandler {
    @ExceptionHandler(NoSuchBuildingException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionInfo notFoundHandler(NoSuchBuildingException ex) {
        LocalDateTime timestamp = LocalDateTime.now();
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.setMessage(ex.getMessage());
        exceptionInfo.setTimestamp(timestamp);
        return exceptionInfo;
    }
}
