package de.netgain.employeemanagementapplication.repository;

import de.netgain.employeemanagementapplication.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Specified {@link JpaRepository} for {@link Employee} entitites.
 *
 * @author MirkoSchulze
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
