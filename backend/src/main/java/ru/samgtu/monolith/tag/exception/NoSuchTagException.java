package ru.samgtu.monolith.tag.exception;

import java.util.NoSuchElementException;

/**
 * Creation date: 09.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public class NoSuchTagException extends NoSuchElementException {
    public NoSuchTagException(String message) {
        super(message);
    }
}