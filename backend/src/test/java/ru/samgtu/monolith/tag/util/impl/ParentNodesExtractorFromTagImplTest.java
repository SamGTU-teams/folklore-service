package ru.samgtu.monolith.tag.util.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
class ParentNodesExtractorFromTagImplTest {
    static ParentNodesExtractorFromTagImpl extractor;

    static String tag;

    static String delimiter;

    @BeforeAll
    static void setUp() {
        tag = "1.2.3.4.5.6";
        delimiter = "\\.";
        extractor = new ParentNodesExtractorFromTagImpl();
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
}