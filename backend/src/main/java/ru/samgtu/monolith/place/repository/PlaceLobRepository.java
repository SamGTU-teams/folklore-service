package ru.samgtu.monolith.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samgtu.monolith.place.model.persistence.PlaceLob;

/**
 * Creation date: 09.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface PlaceLobRepository extends JpaRepository<PlaceLob, Long> {
}
