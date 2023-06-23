package de.netgain.employeemanagementapplication.controller;

import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MirkoSchulze
 */
//@Controller
@Named("employeeController")
@ViewScoped
public class EmployeeController implements Serializable {

    private static final Logger L = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    private Collection<Employee> employees;

    private long selectedEmployee, selectedEmployee2;

    private String firstName, lastName, firstName2, lastName2;

    public String getFirstName() {
        L.info("\ngetting firstname = " + firstName + "\n");
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        L.info("\nsetting firstname = " + firstName + "\n");
    }

    public String getLastName() {
        L.info("\ngetting lastname = " + firstName + "\n");
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        L.info("\nsetting lastname = " + firstName + "\n");
    }

    public long getSelectedEmployee2() {
        L.info("\ngetting selectedEmployee2 = " + selectedEmployee2 + "\n");
        return selectedEmployee2;
    }

    public void setSelectedEmployee2(long selectedEmployee2) {
        this.selectedEmployee2 = selectedEmployee2;
        L.info("\nsetting selectedEmployee2 = " + selectedEmployee2 + "\n");
    }
    public String getFirstName2() {
        L.info("\ngetting firstname2 = " + firstName + "\n");
        return firstName2;
    }

    public void setFirstName2(String firstName2) {
        this.firstName2 = firstName2;
        L.info("\nsetting firstname2 = " + firstName + "\n");
    }

    public String getLastName2() {
        L.info("\ngetting lastname2 = " + firstName + "\n");
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
        L.info("\nsetting lastname2 = " + firstName + "\n");
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    public void setSelectedEmployee(long selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
        L.info("\nsetting selectedEmployee = " + selectedEmployee + "\n");
    }

    public long getSelectedEmployee() {
        L.info("\ngetting selectedEmployee = " + selectedEmployee + "\n");
        return selectedEmployee;
    }

    public void deleteEmployee() {
        L.info(this.getClass().getSimpleName() + ": deleteEmployee(" + getSelectedEmployee() + ")");
        employeeService.deleteEmployee(getSelectedEmployee());
//        refreshView();
    }

    public void saveEmployee() {
        L.info(this.getClass().getSimpleName() + ": saveEmployee()");
        Employee employee = new Employee();
        employee.setFirstName(getFirstName());
        employee.setLastName(getLastName());
        L.info("Employee = " + employee.toString());
        employeeService.saveEmployee(employee);
        refreshView();
    }

    public void updateEmployee() {
        L.info(this.getClass().getSimpleName() + ": updateEmployee(" + selectedEmployee2 + ")");
        L.info(this.getClass().getSimpleName() + ": firstName2 = " + firstName2 + ", lastName2 = " + lastName2 + ", selectedEmployee2 = " + selectedEmployee2);
        Employee employee = new Employee();
        employee.setFirstName(firstName2);
        employee.setLastName(lastName2);
        Employee newEmpl = employeeService.updateEmployee(employee, selectedEmployee2);
        L.info("new Employee = " + newEmpl.toString());
        this.employees = employeeService.getAllEmployees();
        refreshView();
    }

    @PostConstruct
    public void init() {
        L.info(this.getClass().getSimpleName() + ": init()");
        refreshView();
    }
    
    private void refreshView(){
        this.employees = employeeService.getAllEmployees();
    }

}
