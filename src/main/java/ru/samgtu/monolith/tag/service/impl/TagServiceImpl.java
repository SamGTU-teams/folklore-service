package ru.samgtu.monolith.tag.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.samgtu.monolith.tag.model.persistence.Tag;
import ru.samgtu.monolith.tag.service.TagService;

import java.util.ArrayList;
import java.util.List;

/**
 * Creation date: 07.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {
    @Override
    public List<Tag> getTags(int page,
                             int size) {
        ArrayList<Tag> list = new ArrayList<>();
        list.add(getTagById(1L));
        return list;
    }

    @Override
    public List<Tag> getTagsByName(String name,
                                   int page,
                                   int size) {
        return getTags(page, size);
    }

    @Override
    public Tag getTagById(Long id) {
        Tag tag = new Tag();
        tag.setName("default Tag by id");
        tag.setId(1L);
        return tag;
    }
}
