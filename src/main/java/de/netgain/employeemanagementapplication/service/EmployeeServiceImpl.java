package de.netgain.employeemanagementapplication.service;

import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger L = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        L.info("[" + this.getClass().getSimpleName() + "] : getAllEmployees() called");
        List<Employee> employees = employeeRepository.findAll();
        L.info("[" + this.getClass().getSimpleName() + "] : returning Collection<Employee> employees = " + employees.toString());
        return employees;
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        L.info("[" + this.getClass().getSimpleName() + "] : getEmployeeById(long id) called with param = " + id);
        Optional<Employee> employee = employeeRepository.findById(id);
        L.info("[" + this.getClass().getSimpleName() + "] : returning Optional<Employee> employee = " + employee.toString());
        return employee;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        L.info("[" + this.getClass().getSimpleName() + "] : saveEmployee(Employee employee) called with param = " + employee);
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> updateEmployee(long id, Employee employeeData) {
        L.info("[" + this.getClass().getSimpleName() + "] : updateEmployee(long id, Employee employeeData) called with param = " + id + "; " + employeeData);
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.ifPresentOrElse(e -> {
            if(!employeeData.getFirstName().isBlank())e.setFirstName(employeeData.getFirstName());
            if(!employeeData.getLastName().isBlank())e.setLastName(employeeData.getLastName());
            e.setDepartment(employeeData.getDepartment());
            employeeRepository.save(e);
            L.info("[" + this.getClass().getSimpleName() + "] : employee updated");
        }, () -> L.info("[" + this.getClass().getSimpleName() + "] : employee not updated"));
        L.info("[" + this.getClass().getSimpleName() + "] : returning Optional<Employee> employee = " + employee.toString());
        return employee;
    }

    @Override
    public void deleteEmployee(long id) {
        L.info("[" + this.getClass().getSimpleName() + "] : deleteEmployee(long id) called with param = " + id);
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.ifPresent(e -> e.getDepartment().removeEmployee(e));
        employeeRepository.deleteById(id);
    }

}
