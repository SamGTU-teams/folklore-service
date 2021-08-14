package ru.samgtu.monolith.tag.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.Collection;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface TagService {
    Page<Tag> getTags(Pageable pageable);

    Page<Tag> getTagsByName(String name,
                            Pageable pageable);

    Tag getTagById(Long id);

    Collection<Tag> getTagsByIds(Collection<Long> ids);
}
