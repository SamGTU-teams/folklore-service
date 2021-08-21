package ru.samgtu.monolith.tag.util;

import java.util.Collection;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface TagUtil {
    Collection<String> optimizeTags(Collection<String> tags);

    String createRegex(Collection<String> tags);
}
