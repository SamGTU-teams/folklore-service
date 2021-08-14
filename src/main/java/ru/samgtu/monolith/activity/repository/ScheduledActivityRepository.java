package ru.samgtu.monolith.activity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.samgtu.monolith.activity.model.dto.ScheduledActivityDto;
import ru.samgtu.monolith.activity.model.persistence.ScheduledActivity;

public interface ScheduledActivityRepository extends JpaRepository<ScheduledActivity, Long> {

    Page<ScheduledActivity> findByActivityId(Long id, Pageable pageable);

}
