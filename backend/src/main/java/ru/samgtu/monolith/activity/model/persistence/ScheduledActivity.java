package ru.samgtu.monolith.activity.model.persistence;

import lombok.*;
import ru.samgtu.monolith.activity.model.ActivityStatus;

import javax.persistence.*;

/**
 * Creation date: 10.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Entity
@Table(name = "activity_scheduled")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ScheduledActivity {
    @EmbeddedId
    @AttributeOverride(name = "dateTime", column = @Column(name = "date_time", nullable = false))
    @EqualsAndHashCode.Include
    private ScheduledId id;

    @MapsId("activityId")
    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private ActivityStatus status;
}
