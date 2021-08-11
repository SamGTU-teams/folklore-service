package ru.samgtu.monolith.activity.model.persistence;

import lombok.*;
import ru.samgtu.monolith.folklore.model.persistence.Building;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import javax.persistence.*;
import java.time.Duration;
import java.util.List;
import java.util.Set;

/**
 * Creation date: 08.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Entity
@Table(name = "activity")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "lat", nullable = false)
    private Double lat;

    @Column(name = "lon", nullable = false)
    private Double lon;

    @Column(name = "address", length = 80, nullable = false)
    private String address;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "label_url", nullable = false)
    private String labelUrl;

    @ManyToMany
    @JoinTable(name = "activity_tag",
            joinColumns = @JoinColumn(name = "activity_tag"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ToString.Exclude
    private Set<Tag> tags;

    @OneToMany(mappedBy = "activity")
    private List<ScheduledActivity> scheduledActivities;

    @OneToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @Column(name = "duration", nullable = false)
    private Duration duration;
}
