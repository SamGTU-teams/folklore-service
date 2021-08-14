package ru.samgtu.monolith.activity.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Creation date: 14.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
class ActivityStatusTest {
    @BeforeAll
    static void setUp() {
        values = ActivityStatus.values();
        middle = values[values.length / 2];
    }

    static ActivityStatus[] values;

    static ActivityStatus middle;

    @Test
    void getBefore() {
        ActivityStatus[] expected = Arrays.copyOfRange(values, 0, middle.ordinal());

        ActivityStatus[] actual = ActivityStatus.getBefore(middle);

        check(actual, expected);
    }

    @Test
    void getBeforeOrEquals() {
        ActivityStatus[] expected = Arrays.copyOfRange(values, 0, middle.ordinal() + 1);

        ActivityStatus[] actual = ActivityStatus.getBeforeOrEquals(middle);

        check(actual, expected);
    }

    @Test
    void getAfter() {
        ActivityStatus[] expected = Arrays.copyOfRange(values, middle.ordinal() + 1, values.length);

        ActivityStatus[] actual = ActivityStatus.getAfter(middle);

        check(actual, expected);
    }

    @Test
    void getAfterOrEquals() {
        ActivityStatus[] expected = Arrays.copyOfRange(values, middle.ordinal(), values.length);

        ActivityStatus[] actual = ActivityStatus.getAfterOrEquals(middle);

        check(actual, expected);
    }

    void check(ActivityStatus[] actual, ActivityStatus[] expected) {
        assertThat(actual)
                .isNotNull()
                .doesNotContainNull()
                .isEqualTo(expected);
    }
}