package ru.samgtu.monolith.tag.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.samgtu.monolith.tag.model.persistence.Tag;

/**
 * Creation date: 08.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface TagRepository extends JpaRepository<Tag, String> {
    Page<Tag> findByNameStartsWithIgnoreCase(String name, Pageable pageable);

    Page<Tag> findByIdIsStartingWith(String id, Pageable pageable);
}
