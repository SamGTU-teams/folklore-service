package ru.samgtu.monolith.folklore.model.persistence;

import lombok.*;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import javax.persistence.*;
import java.util.Set;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Entity
@Table(name = "building")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Building {
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "building_tag",
            joinColumns = @JoinColumn(name = "building_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ToString.Exclude
    private Set<Tag> tags;
}
