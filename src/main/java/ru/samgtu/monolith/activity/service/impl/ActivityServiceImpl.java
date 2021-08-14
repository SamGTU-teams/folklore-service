package ru.samgtu.monolith.activity.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.activity.repository.ActivityRepository;
import ru.samgtu.monolith.activity.service.ActivityService;
import ru.samgtu.monolith.tag.model.persistence.Tag;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final EntityManager entityManager;

    @Override
    public List<Activity> findByTags(Collection<Tag> tags, Pageable pageable){
        return activityRepository.findByTagsIn(tags, pageable).toList();
    }

    @Override
    public List<Activity> findByDateAfterThan(LocalDateTime from, int size, int page){
        return entityManager.createNamedQuery("select_by_date_more_than", Activity.class).
                setParameter(1, Timestamp.valueOf(from)).
                setParameter(2, size).setParameter(3, (page - 1) * size).getResultList();
    }

    @Override
    public List<Activity> findByName(String name){
        return activityRepository.findByNameStartsWithIgnoreCase(name);
    }

    @Override
    public Optional<Activity> findById(Long id) {
        return activityRepository.findById(id);
    }

    @Override
    public Page<Activity> findByBuildingId(Long id, Pageable pageable) {
        return activityRepository.findAllByBuildingId(id, pageable);
    }

}
