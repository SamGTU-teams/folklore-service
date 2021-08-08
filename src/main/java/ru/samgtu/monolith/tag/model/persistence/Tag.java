package ru.samgtu.monolith.tag.model.persistence;

import lombok.*;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Tag {
    private Long id;

    private String name;
}
