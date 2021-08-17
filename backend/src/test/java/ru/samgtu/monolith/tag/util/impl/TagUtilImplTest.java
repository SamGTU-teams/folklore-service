package ru.samgtu.monolith.tag.util.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

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
    @InjectMocks
    TagUtilImpl tagUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testTagsSet() {
        Collection<String> tags = Arrays.asList("1.2", "1", "1.2.3", "2.2", "2.2.1", "2.2.3", "2.2.4.5.7", "2.23");
        List<String> expected = Arrays.asList("1", "2.2", "2.23");

        Collection<String> actual = tagUtil.optimizeTags(tags);

        assertThat(actual)
                .isNotNull()
                .doesNotContainNull()
                .containsExactlyInAnyOrderElementsOf(expected);
    }
}