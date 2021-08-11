package ru.samgtu.monolith.activity.repository;

import org.springframework.data.repository.CrudRepository;
import ru.samgtu.monolith.activity.model.persistence.Activity;

public interface ActivityRepository extends CrudRepository<Activity, Long> {


}
