package ru.samgtu.monolith.activity.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.samgtu.monolith.activity.model.ActivityStatus;
import ru.samgtu.monolith.activity.model.persistence.ScheduledActivity;
import ru.samgtu.monolith.activity.model.persistence.ScheduledId;
import ru.samgtu.monolith.activity.repository.ScheduledActivityRepository;
import ru.samgtu.monolith.activity.service.ScheduledActivityService;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Creation date: 14.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledActivityServiceImpl implements ScheduledActivityService {
    private final ScheduledActivityRepository repository;

    @Transactional
    @Scheduled(fixedDelayString = "${activity.status.rate}")
    public void checkStatuses() {
        log.info("Start check statuses");
        LocalDateTime now = LocalDateTime.now();
        for (ScheduledId id : repository.findAllIds()) {
            Optional<ScheduledActivity> optionalScheduledActivity = repository.findById(id);
            if (!optionalScheduledActivity.isPresent()) {
                continue;
            }
            ScheduledActivity scheduledActivity = optionalScheduledActivity.get();
            LocalDateTime dateTime = scheduledActivity.getId().getDateTime();
            Duration duration = scheduledActivity.getActivity().getDuration();
            LocalDateTime endDateTime;
            if (now.compareTo(dateTime) < 1) {
                setStatus(scheduledActivity, ActivityStatus.SCHEDULED);
            } else if (now.compareTo(endDateTime = dateTime.plus(duration)) < 1) {
                setStatus(scheduledActivity, ActivityStatus.STARTED);
            } else {
                setStatus(scheduledActivity, ActivityStatus.ENDED);
            }
        }
        log.info("Finish check statuses");
    }

    private void setStatus(ScheduledActivity scheduledActivity, ActivityStatus status) {
        ActivityStatus currentStatus = scheduledActivity.getStatus();
        if (status.equals(currentStatus)) {
            return;
        }
        log.debug("Changing the status of the object with id = {} from {} to {} ",
                scheduledActivity.getId(), currentStatus, status);
        scheduledActivity.setStatus(status);
        repository.save(scheduledActivity);
    }
}
