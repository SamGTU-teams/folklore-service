package ru.samgtu.monolith.activity.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Creation date: 08.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public enum ActivityStatus {
    SCHEDULED,
    STARTED,
    ENDED;

    public static ActivityStatus[] getBefore(ActivityStatus value) {
        return toArray(getBeforeStream(value));
    }

    public static ActivityStatus[] getBeforeOrEquals(ActivityStatus value) {
        return toArray(Stream.concat(getBeforeStream(value), Stream.of(value)));
    }

    public static ActivityStatus[] getAfter(ActivityStatus value) {
        return toArray(getAfterStream(value));
    }

    public static ActivityStatus[] getAfterOrEquals(ActivityStatus value) {
        return toArray(Stream.concat(getAfterStream(value), Stream.of(value)));
    }

    private static ActivityStatus[] toArray(Stream<ActivityStatus> stream) {
        return stream.sorted(Comparator.comparingInt(Enum::ordinal))
                .toArray(ActivityStatus[]::new);
    }

    private static Stream<ActivityStatus> getBeforeStream(ActivityStatus value) {
        return Arrays.stream(ActivityStatus.values())
                .filter(status -> status.ordinal() < value.ordinal());
    }

    private static Stream<ActivityStatus> getAfterStream(ActivityStatus value) {
        return Arrays.stream(ActivityStatus.values())
                .filter(status -> status.ordinal() > value.ordinal());
    }
}
