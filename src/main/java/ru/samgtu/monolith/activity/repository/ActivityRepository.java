package ru.samgtu.monolith.activity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.samgtu.monolith.activity.model.persistence.Activity;

import java.time.LocalDateTime;

public interface ActivityRepository extends JpaRepository<Activity, Long> {



}
