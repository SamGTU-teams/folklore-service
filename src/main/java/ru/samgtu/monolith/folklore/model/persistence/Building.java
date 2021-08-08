package ru.samgtu.monolith.folklore.model.persistence;

import lombok.*;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.List;

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
public class Building {
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private Double lat;

    private Double lon;

    @ToString.Exclude
    private List<Tag> tags;

    private String address;

    private String imageUrl;

    private String labelUrl;
}
