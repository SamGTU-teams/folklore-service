package ru.samgtu.monolith.tag.service;

import org.springframework.data.domain.Pageable;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.List;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface TagService {
    List<Tag> getTags(Pageable pageable);

    List<Tag> getTagsByName(String name,
                            Pageable pageable);

    Tag getTagById(Long id);
}
