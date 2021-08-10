package ru.samgtu.monolith.folklore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samgtu.monolith.folklore.model.persistence.BuildingLob;

/**
 * Creation date: 09.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
public interface BuildingLobRepository extends JpaRepository<BuildingLob, Long> {
}
