package ru.samgtu.monolith.activity.model.persistence;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Creation date: 10.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Embeddable
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ScheduledId implements Serializable {
    @EqualsAndHashCode.Include
    private Long activityId;

    @EqualsAndHashCode.Include
    private LocalDateTime dateTime;
}
