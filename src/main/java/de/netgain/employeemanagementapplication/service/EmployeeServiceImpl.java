package de.netgain.employeemanagementapplication.service;

import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.repository.EmployeeRepository;
import java.util.Collection;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger L = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Collection<Employee> getAllEmployees() {
        L.debug("[" + this.getClass().getSimpleName() + "] : getAllEmployees() called");
        Collection<Employee> employees = employeeRepository.findAll();
        L.debug("[" + this.getClass().getSimpleName() + "] : returning Collection<Employee> employees = " + employees.toString());
        return employees;
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        L.debug("[" + this.getClass().getSimpleName() + "] : getEmployeeById(long id) called with param = " + id);
        Optional<Employee> employee = employeeRepository.findById(id);
        L.debug("[" + this.getClass().getSimpleName() + "] : returning Optional<Employee> employee = " + employee.toString());
        return employee;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        L.debug("[" + this.getClass().getSimpleName() + "] : saveEmployee(Employee employee) called with param = " + employee.toSimpleName());
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> updateEmployee(long id, Employee employeeData) {
        L.debug("[" + this.getClass().getSimpleName() + "] : updateEmployee(long id, Employee employeeData) called with param = " + id + "; " + employeeData.toSimpleName());
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.ifPresentOrElse(e -> {
            e.setFirstName(employeeData.getFirstName());
            e.setLastName(employeeData.getLastName());
            employeeRepository.save(e);
            L.debug("[" + this.getClass().getSimpleName() + "] : employee updated");
        }, () -> L.debug("[" + this.getClass().getSimpleName() + "] : employee not updated"));
        L.debug("[" + this.getClass().getSimpleName() + "] : returning Optional<Employee> employee = " + employee.toString());
        return employee;
    }

    @Override
    public void deleteEmployee(long id) {
        L.debug("[" + this.getClass().getSimpleName() + "] : deleteEmployee(long id) called with param = " + id);
        employeeRepository.deleteById(id);
    }

}
