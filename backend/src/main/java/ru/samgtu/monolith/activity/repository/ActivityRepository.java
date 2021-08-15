package ru.samgtu.monolith.activity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.util.Collection;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Page<Activity> findByNameStartsWithIgnoreCase(String name, Pageable pageable);

    Page<Activity> findAllByBuildingId(Long id, Pageable pageable);

    Page<Activity> findByTagsIn(Collection<Tag> tags, Pageable pageable);

    Collection<Activity> findActivityByIdIn(Collection<Long> ids);
}
