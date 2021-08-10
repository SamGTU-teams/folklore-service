package ru.samgtu.monolith.folklore.model.persistence;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Creation date: 09.08.2021
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
public class BuildingLob {
    @Id
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne
    @JoinColumn(name = "id", nullable = false)
    @MapsId
    private Building building;

    @Lob
    @Basic
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "description", nullable = false)
    @ToString.Exclude
    private String description;

    @Lob
    @Basic
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "media_urls", nullable = false)
    @ToString.Exclude
    private String mediaUrls;
}
