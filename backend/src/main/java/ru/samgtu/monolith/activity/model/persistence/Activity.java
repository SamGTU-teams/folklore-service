package ru.samgtu.monolith.activity.model.persistence;

import lombok.*;
import ru.samgtu.monolith.model.persistence.DescriptionAndUrlsLob;
import ru.samgtu.monolith.place.model.persistence.Place;
import ru.samgtu.monolith.region.model.persistence.Region;
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

@Table(name = "activity")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
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

    @Column(name = "can_visit", nullable = false)
    private boolean canVisit;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "label_url", nullable = false)
    private String labelUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "activity_tag",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ToString.Exclude
    private Set<Tag> tags;

    @ToString.Exclude
    @OneToMany(mappedBy = "activity", fetch = FetchType.EAGER)
    private List<ScheduledActivity> scheduledActivities;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lob_id", referencedColumnName = "id")
    private DescriptionAndUrlsLob lob;

    @OneToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(name = "duration", nullable = false)
    private Duration duration;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @OneToOne
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private Region region;
}
