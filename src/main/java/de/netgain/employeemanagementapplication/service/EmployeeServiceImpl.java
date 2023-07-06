package de.netgain.employeemanagementapplication.service;

import de.netgain.employeemanagementapplication.model.Department;
import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation of {@link EmployeeService}.
 *
 * @author MirkoSchulze
 */
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger L = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        L.debug("[{}] : getAllEmployees() called", this.getClass().getSimpleName());
        List<Employee> employees = employeeRepository.findAll();
        L.debug("[{}] : returning List<Employee> employees = {}", this.getClass().getSimpleName(), employees.toString());
        return employees;
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        L.debug("[{}] : getEmployeeById(long id) called with param = {}", this.getClass().getSimpleName(), id);
        Optional<Employee> employee = employeeRepository.findById(id);
        L.debug("[{}] : returning Optional<Employee> employee = {}", this.getClass().getSimpleName(), employee.toString());
        return employee;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        L.debug("[{}] : saveEmployee(Employee employee) called with param = {}", this.getClass().getSimpleName(), employee.toString());
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> updateEmployee(long id, Employee employeeData) {
        L.debug("[{}] : updateEmployee(long id, Employee employeeData) called with param =  {}; {}", this.getClass().getSimpleName(), id, employeeData.toString());
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.ifPresentOrElse(e -> {
            if (employeeData.getFirstName() != null && !employeeData.getFirstName().isBlank()) {
                e.setFirstName(employeeData.getFirstName());
            }
            if (employeeData.getLastName() != null && !employeeData.getLastName().isBlank()) {
                e.setLastName(employeeData.getLastName());
            }
            if (employeeData.getDepartment() != null && e.getDepartment() != null) {
                e.setDepartment(employeeData.getDepartment());
            }
            employeeRepository.save(e);
            L.debug("[{}] : employee updated to {}", this.getClass().getSimpleName(), e.toString());
        }, () -> L.debug("[{}] : employee not updated", this.getClass().getSimpleName()));
        L.debug("[{}] : returning Optional<Employee> employee = {}", this.getClass().getSimpleName(), employee.toString());
        return employee;
    }

    @Override
    public void deleteEmployee(long id) {
        L.debug("[{}] : deleteEmployee(long id) called with param = {}", this.getClass().getSimpleName(), id);
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.ifPresentOrElse(e -> {
            Department d = e.getDepartment();
            if (d != null) {
                d.removeEmployee(e);
                L.debug("[{}] : removed employee {} from department {}",
                        this.getClass().getSimpleName(), e.toString(), d.toString());
            }
            employeeRepository.deleteById(id);
            L.debug("[{}] : employee deleted", this.getClass().getSimpleName());
        }, () -> L.debug("[{}] : employee not deleted", this.getClass().getSimpleName()));

    }

}
