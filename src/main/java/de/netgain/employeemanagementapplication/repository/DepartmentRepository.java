package de.netgain.employeemanagementapplication.repository;

import de.netgain.employeemanagementapplication.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Specified {@link JpaRepository} for {@link Department} entitites.
 *
 * @author MirkoSchulze
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
