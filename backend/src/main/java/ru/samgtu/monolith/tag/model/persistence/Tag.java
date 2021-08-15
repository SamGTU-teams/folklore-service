package ru.samgtu.monolith.tag.model.persistence;

import lombok.*;
import ru.samgtu.monolith.place.model.persistence.Place;

import javax.persistence.*;
import java.util.Set;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Entity
@Table(name = "tag")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", nullable = false, length = 20, unique = true)
    private String name;
}
