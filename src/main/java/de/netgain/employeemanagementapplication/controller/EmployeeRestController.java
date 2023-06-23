package de.netgain.employeemanagementapplication.controller;

import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.service.EmployeeService;
import java.util.Collection;
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

/**
 *
 * @author MirkoSchulze
 */
@RestController
@RequestMapping("/management")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public Collection<Employee> readEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/employees/{empId}")
    public Employee readEmployee(@PathVariable(value = "empId") long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping(value = "/employees/{empId}")
    public Employee updateEmployee(@PathVariable(value = "empId") long id, @RequestBody Employee empUpdate) {
        return employeeService.updateEmployee(empUpdate, id);
    }

    @DeleteMapping(value = "/employees/{empId}")
    public void deleteEmployee(@PathVariable(value = "empId") long id) {
        employeeService.deleteEmployee(id);
    }

}
