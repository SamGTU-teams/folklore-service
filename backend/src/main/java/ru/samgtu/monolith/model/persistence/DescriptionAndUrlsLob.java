package ru.samgtu.monolith.model.persistence;

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
@Table(name = "description_url")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class DescriptionAndUrlsLob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

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
