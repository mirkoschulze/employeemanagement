package de.netgain.employeemanagementapplication.service;

import de.netgain.employeemanagementapplication.model.Employee;
import java.util.Collection;

public interface EmployeeService {
    
    public Collection<Employee> getAllEmployees();
    
    public Employee getEmployeeById(long id);
    
    public Employee saveEmployee(Employee employee);
    
    public void deleteEmployee(long id);
    
    public Employee updateEmployee(Employee employee, long id);

}
