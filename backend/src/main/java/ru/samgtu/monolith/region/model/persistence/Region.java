package ru.samgtu.monolith.region.model.persistence;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Creation date: 16.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Entity
@Table(name = "region")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @Lob
    @Basic
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "points", nullable = false)
    @ToString.Exclude
    private String points;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
}
