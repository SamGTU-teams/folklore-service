package ru.samgtu.monolith.region;

import java.util.NoSuchElementException;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public class NoSuchRegionException extends NoSuchElementException {
    public NoSuchRegionException(String message) {
        super(message);
    }
}