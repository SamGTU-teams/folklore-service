package ru.samgtu.monolith.tag.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.samgtu.monolith.tag.model.persistence.Tag;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface TagService {
    Page<Tag> findChildrenTagsById(String id, Pageable pageable);

    Page<Tag> findTagsByName(String name,
                             Pageable pageable);

    Tag findTagById(String id);
}
