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

    public List<Department> getAllDepartments();

    public Optional<Department> getDepartmentById(long id);

    public Department saveDepartment(Department department);

    public Optional<Department> updateDepartment(long id, Department departmentData);

    public void deleteDepartment(long id);

}
