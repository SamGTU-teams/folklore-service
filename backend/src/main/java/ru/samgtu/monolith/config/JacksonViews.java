package ru.samgtu.monolith.config;

/**
 * Creation date: 14.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public final class JacksonViews {
    public interface Public {
    }

    public interface DataWithoutLob extends Public {
    }

    public interface DataWithLob extends DataWithoutLob {
    }
}
