package ru.samgtu.monolith.place.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.samgtu.monolith.place.controller.PlaceController;
import ru.samgtu.monolith.place.exception.NoSuchPlaceException;
import ru.samgtu.monolith.model.ExceptionInfo;

import java.time.LocalDateTime;

/**
 * Creation date: 09.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RestControllerAdvice(assignableTypes = PlaceController.class)
public class PlaceControllerHandler {
    @ExceptionHandler(NoSuchPlaceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionInfo notFoundHandler(NoSuchPlaceException ex) {
        LocalDateTime timestamp = LocalDateTime.now();
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.setMessage(ex.getMessage());
        exceptionInfo.setTimestamp(timestamp);
        return exceptionInfo;
    }
}
