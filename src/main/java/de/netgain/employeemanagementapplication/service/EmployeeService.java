package de.netgain.employeemanagementapplication.service;

import de.netgain.employeemanagementapplication.model.Employee;
import java.util.Collection;
import java.util.Optional;

public interface EmployeeService {

    public Collection<Employee> getAllEmployees();

    public Optional<Employee> getEmployeeById(long id);

    public Employee saveEmployee(Employee employee);

    public Optional<Employee> updateEmployee(long id, Employee employeeData);

    public void deleteEmployee(long id);

}
