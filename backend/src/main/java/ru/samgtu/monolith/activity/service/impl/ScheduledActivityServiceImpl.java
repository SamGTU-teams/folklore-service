package ru.samgtu.monolith.activity.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.samgtu.monolith.activity.model.ActivityStatus;
import ru.samgtu.monolith.activity.model.persistence.Activity;
import ru.samgtu.monolith.activity.model.persistence.ScheduledActivity;
import ru.samgtu.monolith.activity.model.persistence.ScheduledId;
import ru.samgtu.monolith.activity.repository.ActivityRepository;
import ru.samgtu.monolith.activity.repository.ScheduledActivityRepository;
import ru.samgtu.monolith.activity.service.ScheduledActivityService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final ScheduledActivityRepository scheduledActivityRepository;

    private final ActivityRepository activityRepository;

    @Transactional
    @Scheduled(fixedDelayString = "${activity.status.rate}")
    public void checkStatuses() {
        log.info("Start check statuses");
        LocalDateTime now = LocalDateTime.now();
        Pageable pageRequest = PageRequest.of(0, 200);
        Page<ScheduledActivity> activities = scheduledActivityRepository.findAll(pageRequest);
        while (activities.hasContent()) {
            List<ScheduledActivity> updateActivities = new LinkedList<>();
            for (ScheduledActivity activity : activities.getContent()) {
                LocalDateTime dateTime = activity.getId().getDateTime();
                Duration duration = activity.getActivity().getDuration();
                boolean canVisit = activity.getActivity().isCanVisit();
                ActivityStatus currentStatus = activity.getStatus();
                ActivityStatus newStatus = calcStatus(now, dateTime, duration, canVisit);

                if (!currentStatus.equals(newStatus)) {
                    log.debug("Changing the status of the object with id = {} from {} to {} ",
                            activity.getId(), currentStatus, newStatus);
                    activity.setStatus(newStatus);
                    updateActivities.add(activity);
                }
            }
            scheduledActivityRepository.saveAll(updateActivities);
            if(activities.hasNext()) {
                pageRequest = activities.nextPageable();
                activities = scheduledActivityRepository.findAll(pageRequest);
            } else {
                break;
            }
        }

        log.info("Finish check statuses");
    }

    @Override
    public Page<ScheduledActivity> findByDateAfterThanEqual(LocalDateTime from, Pageable pageable) {
        Page<Object[]> page = scheduledActivityRepository.findMinDateTimeIsGreaterThanEqualOrderByIdDateTimeAsc(from, pageable);
        return formActivities(page, pageable);
    }

    @Override
    public Page<ScheduledActivity> findByDateAfterThanEqual(LocalDateTime from, Set<ActivityStatus> statuses, Pageable pageable) {
        Page<Object[]> page = scheduledActivityRepository.findMinDateTimeIsGreaterThanEqualOrderByIdDateTimeAsc(from, statuses, pageable);
        return formActivities(page, pageable);
    }

    private Page<ScheduledActivity> formActivities(Page<Object[]> page, Pageable pageable){
        // ToDo: create cache table
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Map<Long, LocalDateTime> map = page.stream()
                .collect(Collectors.toMap(obj -> (Long) obj[0], obj -> (LocalDateTime) obj[1]));

        List<Activity> activities = activityRepository.findAllById(map.keySet());

        LocalDateTime now = LocalDateTime.now();
        List<ScheduledActivity> scheduledActivities =
                activities.stream().map(activity -> {
                    Long activityId = activity.getId();
                    LocalDateTime dateTime = map.get(activityId);
                    Duration duration = activity.getDuration();
                    boolean canVisit = activity.isCanVisit();

                    ScheduledId id = new ScheduledId();
                    id.setActivityId(activityId);
                    id.setDateTime(dateTime);

                    ScheduledActivity scheduledActivity = new ScheduledActivity();
                    ActivityStatus status = calcStatus(now, dateTime, duration, canVisit);
                    scheduledActivity.setActivity(activity);
                    scheduledActivity.setStatus(status);

                    return scheduledActivity;
                }).collect(Collectors.toList());

        return new PageImpl<>(scheduledActivities, pageable, page.getTotalElements());
    }

    @Override
    public Page<ScheduledActivity> findScheduledByActivityId(Long id, Pageable pageable) {
        return scheduledActivityRepository.findByActivityId(id, pageable);
    }

    @Override
    public Page<ScheduledActivity> findScheduledByActivityId(Long id, Set<ActivityStatus> statuses, Pageable pageable) {
        return scheduledActivityRepository.findAllByActivityIdAndStatusIn(id, statuses, pageable);
    }

    private ActivityStatus calcStatus(LocalDateTime now, LocalDateTime dateTime, Duration duration, boolean canVisited) {
        if (now.compareTo(dateTime) < 1) {
            return ActivityStatus.SCHEDULED;
        } else if (now.compareTo(dateTime.plus(duration)) < 1) {
            return canVisited ? ActivityStatus.STARTED_CAN_VISIT : ActivityStatus.STARTED_CANT_VISIT;
        } else {
            return ActivityStatus.ENDED;
        }
    }

    private void setStatus(ScheduledActivity scheduledActivity, ActivityStatus status) {
        ActivityStatus currentStatus = scheduledActivity.getStatus();
        if (status.equals(currentStatus)) {
            return;
        }
        log.debug("Changing the status of the object with id = {} from {} to {} ",
                scheduledActivity.getId(), currentStatus, status);
        scheduledActivity.setStatus(status);
        scheduledActivityRepository.save(scheduledActivity);
    }
}
