package ru.samgtu.monolith.activity;

import java.util.NoSuchElementException;

/**
 * Creation date: 14.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public class NoSuchActivityException extends NoSuchElementException {
    public NoSuchActivityException(String message) {
        super(message);
    }
}