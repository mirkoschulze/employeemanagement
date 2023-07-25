package de.netgain.employeemanagementapplication.service;

import de.netgain.employeemanagementapplication.model.Employee;
import java.util.List;
import java.util.Optional;

/**
 * This interface defines the JPA operations involving {@link Employee}s that
 * are needed for this application.
 *
 * @author MirkoSchulze
 */
public interface EmployeeService {

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(long id);

    Employee saveEmployee(Employee employee);

    Optional<Employee> updateEmployee(long id, Employee employeeData);

//    @Secured("ADMIN")
    void deleteEmployee(long id);

}
