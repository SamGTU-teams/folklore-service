package ru.samgtu.monolith.tag.util.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.samgtu.monolith.activity.model.ActivityStatus;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
class ParentNodesExtractorFromTagImplTest {
    @BeforeAll
    static void setUp() {
        tag = "1.2.3.4.5.6";
        delimiter = ".";
        extractor = new ParentNodesExtractorFromTagImpl(delimiter);
    }

    static ParentNodesExtractorFromTagImpl extractor;

    static String tag;

    static String delimiter;

    @Test
    void extractFrom() {
        List<String> expected = Arrays.asList("1", "1.2", "1.2.3", "1.2.3.4", "1.2.3.4.5");
        List<String> parents = extractor.extractFrom(tag);

        assertThat(parents)
                .isNotNull()
                .isEqualTo(expected);
    }
}