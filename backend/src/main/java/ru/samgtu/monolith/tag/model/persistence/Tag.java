package ru.samgtu.monolith.tag.model.persistence;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    @Column(name = "id", length = 14, nullable = false)
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "name", nullable = false, length = 31)
    private String name;
}
