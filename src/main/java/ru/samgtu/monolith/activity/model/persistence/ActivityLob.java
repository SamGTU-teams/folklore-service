package ru.samgtu.monolith.activity.model.persistence;

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
@Table(name = "activity")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ActivityLob {
    @Id
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne
    @JoinColumn(name = "id", nullable = false)
    @MapsId
    private Activity activity;

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