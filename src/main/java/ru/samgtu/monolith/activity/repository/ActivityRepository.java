package ru.samgtu.monolith.activity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.tag.model.dto.TagDto;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByNameStartsWithIgnoreCase(String name);

    Page<Activity> findAllByBuildingId(Long id, Pageable pageable);

    Page<Activity> findByTagsIn(Collection<Tag> tags, Pageable pageable);

}
