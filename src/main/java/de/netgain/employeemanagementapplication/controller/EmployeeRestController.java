package de.netgain.employeemanagementapplication.controller;

import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.service.EmployeeService;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contains logic to execute JPA operations with {@link Employee}s via REST.
 *
 * @author MirkoSchulze
 */
@RestController
@RequestMapping(value = "/employees")
public class EmployeeRestController {

    private static final Logger L = LoggerFactory.getLogger(EmployeeRestController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * Get all {@link Employee} entities.
     *
     * @return A List containing all found Employees.
     */
    @GetMapping
    public Collection<Employee> readEmployees() {
        L.debug("[" + this.getClass().getSimpleName() + "] : readEmployees() called");
        return employeeService.getAllEmployees();
    }

    /**
     * Get the {@link Employee} with the submitted id.
     *
     * @param id Id of the requested Employee.
     * @return The requested Employee.
     *
     */
    @GetMapping(value = "/{empId}")
    public Employee readEmployee(@PathVariable(value = "empId") long id) {
        L.debug("[" + this.getClass().getSimpleName() + "] : readEmployee(long id) called with param = " + id);
        return employeeService.getEmployeeById(id).get();
    }

    /**
     * Submit an {@link Employee} entity to persist.
     *
     * @param employee The Employee to persist.
     * @return The persisted Empoyee.
     */
    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        L.debug("[" + this.getClass().getSimpleName() + "] : saveEmployee(Employee employee) called with param = " + employee);
        return employeeService.saveEmployee(employee);
    }

    /**
     * Submit an {@link Employee} entity to merge it with one in the database.
     *
     * @param id Id of the Employee to be merged.
     * @param employeeData Employee with new data
     * @return The merged Employee.
     */
    @PutMapping(value = "/{empId}")
    public Employee updateEmployee(@PathVariable(value = "empId") long id, @RequestBody Employee employeeData) {
        L.debug("[" + this.getClass().getSimpleName() + "] : updateEmployee(long id, Employee employeeData) called with param = " + id + ", " + employeeData);
        return employeeService.updateEmployee(id, employeeData).get();
    }

    /**
     * Delete an {@link Employee} entity from the database.
     *
     * @param id Id of the Employee to be deleted.
     */
    @DeleteMapping(value = "/{empId}")
    public void deleteEmployee(@PathVariable(value = "empId") long id) {
        L.debug("[" + this.getClass().getSimpleName() + "] : deleteEmployees(long id) called with param = " + id);
        employeeService.deleteEmployee(id);
    }

}
