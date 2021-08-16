package ru.samgtu.monolith.tag.util;

import java.util.List;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface ParentNodesExtractorFromTag {
    List<String> extractFrom(String tag);
}
