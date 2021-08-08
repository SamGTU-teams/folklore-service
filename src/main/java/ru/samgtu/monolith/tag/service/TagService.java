package ru.samgtu.monolith.tag.service;

import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.List;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface TagService {
    List<Tag> getTags(int page,
                      int size);

    List<Tag> getTagsByName(String name,
                            int page,
                            int size);

    Tag getTagById(Long id);
}
