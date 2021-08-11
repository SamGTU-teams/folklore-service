package ru.samgtu.monolith.tag.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.samgtu.monolith.model.ExceptionInfo;
import ru.samgtu.monolith.tag.controller.TagController;
import ru.samgtu.monolith.tag.exception.NoSuchTagException;

import java.time.LocalDateTime;

/**
 * Creation date: 09.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RestControllerAdvice(assignableTypes = TagController.class)
public class TagControllerHandler {
    @ExceptionHandler(NoSuchTagException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionInfo notFoundHandler(NoSuchTagException ex) {
        LocalDateTime timestamp = LocalDateTime.now();
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.setMessage(ex.getMessage());
        exceptionInfo.setTimestamp(timestamp);
        return exceptionInfo;
    }
}
