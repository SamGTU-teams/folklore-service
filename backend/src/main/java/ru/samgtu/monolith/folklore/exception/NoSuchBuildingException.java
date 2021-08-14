package ru.samgtu.monolith.folklore.exception;

import java.util.NoSuchElementException;

/**
 * Creation date: 09.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public class NoSuchBuildingException extends NoSuchElementException {
    public NoSuchBuildingException(String message) {
        super(message);
    }
}