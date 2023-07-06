package de.netgain.employeemanagementapplication.service;

import de.netgain.employeemanagementapplication.model.Department;
import java.util.List;
import java.util.Optional;

/**
 * This interface defines the JPA operations involving {@link Department}s that
 * are needed for this application.
 *
 * @author MirkoSchulze
 */
public interface DepartmentService {

    List<Department> getAllDepartments();

    Optional<Department> getDepartmentById(long id);

    Department saveDepartment(Department department);

    Optional<Department> updateDepartment(long id, Department departmentData);

    void deleteDepartment(long id);

}
