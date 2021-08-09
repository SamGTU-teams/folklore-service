package ru.samgtu.monolith.activity.model.persistence;

import lombok.*;

/**
 * Creation date: 09.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ActivityLob {
    @EqualsAndHashCode.Include
    private Long id;

    private Activity activity;

    @ToString.Exclude
    private String description;

    @ToString.Exclude
    private String mediaUrls;
}