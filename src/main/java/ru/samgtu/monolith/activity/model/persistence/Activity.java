package ru.samgtu.monolith.activity.model.persistence;

import lombok.*;
import ru.samgtu.monolith.activity.model.InteractionType;
import ru.samgtu.monolith.folklore.model.dto.BuildingDto;
import ru.samgtu.monolith.tag.model.dto.TagDto;
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
    @JoinTable(name = "building_tag",
            joinColumns = @JoinColumn(name = "building_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ToString.Exclude
    private Set<Tag> tags;

    @OneToOne
    @Column(name = "building_id")
    private BuildingDto building;

    @Column(name = "duration", nullable = false)
    private Duration duration;

    @Column(name = "interaction", nullable = false)
    private InteractionType interactionType;
}
