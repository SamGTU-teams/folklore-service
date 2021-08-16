package ru.samgtu.monolith.place.model.persistence;

import lombok.*;
import ru.samgtu.monolith.model.persistence.DescriptionAndUrlsLob;
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
@Table(name = "place")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Place {
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

    @Column(name = "canVisit", nullable = false)
    private boolean canVisit;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "label_url", nullable = false)
    private String labelUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "place_tag",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ToString.Exclude
    private Set<Tag> tags;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lob_id", referencedColumnName = "id")
    private DescriptionAndUrlsLob lob;
}
