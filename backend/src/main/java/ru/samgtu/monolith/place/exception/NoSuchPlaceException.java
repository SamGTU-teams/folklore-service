package ru.samgtu.monolith.place.exception;

import java.util.NoSuchElementException;

/**
 * Creation date: 09.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public class NoSuchPlaceException extends NoSuchElementException {
    public NoSuchPlaceException(String message) {
        super(message);
    }
}