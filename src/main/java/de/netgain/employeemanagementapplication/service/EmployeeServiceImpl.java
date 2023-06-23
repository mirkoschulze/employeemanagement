package de.netgain.employeemanagementapplication.service;

import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.repository.EmployeeRepository;
import java.util.Collection;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MirkoSchulze
 */
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger L = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Collection<Employee> getAllEmployees() {
        L.info(this.getClass().getSimpleName() + ": getAllEmployees()");
        Collection<Employee> employees = employeeRepository.findAll();
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append(": employeeRepository.findAll(): ");
        if (!employees.isEmpty()) {
            employees.forEach(e -> sb.append(e.toString()));
        } else {
            sb.append("Collection is empty");
        }
        L.info(sb.toString());
        return employees;
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            return null;
        }
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        emp.ifPresentOrElse(e -> {e.setFirstName(employee.getFirstName());
        e.setLastName(employee.getLastName());employeeRepository.save(e);L.info("employee updated");}, () -> L.error("employee not updated"));
//        employeeRepository.flush();
        return emp.get();
    }

}
