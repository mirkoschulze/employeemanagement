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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
public class EmployeeRestController {

    private static final Logger L = LoggerFactory.getLogger(EmployeeRestController.class);

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public Collection<Employee> readEmployees() {
        L.debug("[" + this.getClass().getSimpleName() + "] : readEmployees() called");
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/employees/{empId}")
    public Employee readEmployee(@PathVariable(value = "empId") long id) {
        L.debug("[" + this.getClass().getSimpleName() + "] : readEmployee(long id) called with param = " + id);
        return employeeService.getEmployeeById(id).get();
    }

    @PostMapping(value = "/employees")
    public Employee saveEmployee(@RequestBody Employee employee) {
        L.debug("[" + this.getClass().getSimpleName() + "] : saveEmployee(Employee employee) called with param = " + employee.toSimpleName());
        return employeeService.saveEmployee(employee);
    }

    @PutMapping(value = "/employees/{empId}")
    public Employee updateEmployee(@PathVariable(value = "empId") long id, @RequestBody Employee employeeData) {
        L.debug("[" + this.getClass().getSimpleName() + "] : updateEmployee(long id, Employee employeeData) called with param = " + id + ", " + employeeData);
        return employeeService.updateEmployee(id, employeeData).get();
    }

    @DeleteMapping(value = "/employees/{empId}")
    public void deleteEmployee(@PathVariable(value = "empId") long id) {
        L.debug("[" + this.getClass().getSimpleName() + "] : deleteEmployees(long id) called with param = " + id);
        employeeService.deleteEmployee(id);
    }

}
