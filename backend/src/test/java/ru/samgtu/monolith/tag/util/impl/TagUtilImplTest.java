package ru.samgtu.monolith.tag.util.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
class TagUtilImplTest {
    static TagUtilImpl extractor;

    static String tag;

    static String delimiter;

    @BeforeAll
    static void setUp() {
        tag = "1.2.3.4.5.6";
        delimiter = "\\.";
        extractor = new TagUtilImpl();
        extractor.setDelimiter(delimiter);
    }

    @Test
    void extractFrom() {
        List<String> expected = Arrays.asList("1", "1.2", "1.2.3", "1.2.3.4", "1.2.3.4.5");
        List<String> parents = extractor.extractFrom(tag);

        assertThat(parents)
                .isNotNull()
                .isEqualTo(expected);
    }

    @Test
    void testTagsSet() {
        Collection<String> tags = Stream.of("1.2", "1", "1.2.3", "2.2", "2.2.1", "2.2.3", "2.2.4.5.7").collect(Collectors.toList());
        System.out.println(extractor.optimizeTags(tags));
    }


}