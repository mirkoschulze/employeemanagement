package de.netgain.employeemanagementapplication.controller;

import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
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
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getSelectedEmployee2() {
        return selectedEmployee2;
    }

    public void setSelectedEmployee2(long selectedEmployee2) {
        this.selectedEmployee2 = selectedEmployee2;
    }

    public String getFirstName2() {
        return firstName2;
    }

    public void setFirstName2(String firstName2) {
        this.firstName2 = firstName2;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    public void setSelectedEmployee(long selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public long getSelectedEmployee() {
        return selectedEmployee;
    }

    public void deleteEmployee() {
        L.info(this.getClass().getSimpleName() + ": deleteEmployee(" + getSelectedEmployee() + ")");
        employeeService.deleteEmployee(getSelectedEmployee());
        refreshView();
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
        Employee employeeData = new Employee();
        employeeData.setFirstName(firstName2);
        employeeData.setLastName(lastName2);
        if (checkOptional(employeeService.getEmployeeById(selectedEmployee2))) {
            employeeService.updateEmployee(selectedEmployee2, employeeData);
        } 
        refreshView();
    }

    @PostConstruct
    public void init() {
        L.info(this.getClass().getSimpleName() + ": init()");
        refreshView();
    }

    private void refreshView() {
        this.employees = employeeService.getAllEmployees();
    }

    private boolean optionalNotFound;

    public boolean isOptionalNotFound() {
        L.info("flag is " + optionalNotFound);
        return optionalNotFound;
    }

//    public void setOptionalNotFound(boolean flag) {
//        this.optionalNotFound = flag;
//    }

    public  boolean checkOptional(Optional<Employee> employee) {
        L.info("checkopt");
        employee.ifPresentOrElse(e ->{
            optionalNotFound =false;
        }, () -> {
            optionalNotFound = true;
        });
        return optionalNotFound;
    }

}
