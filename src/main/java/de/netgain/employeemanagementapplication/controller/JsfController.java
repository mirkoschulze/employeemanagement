package de.netgain.employeemanagementapplication.controller;

import de.netgain.employeemanagementapplication.model.Department;
import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.service.DepartmentService;
import de.netgain.employeemanagementapplication.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Named("jsfController")
@ViewScoped
public class JsfController implements Serializable {

    private static final Logger L = LoggerFactory.getLogger(JsfController.class);

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private DepartmentService departmentService;

    private Collection<Employee> employees;

    private Collection<Department> departments;

    private Department selectedDepartment, updateSelectedDepartment;

    private long updateId, deleteId;

    private String saveFirstName, saveLastName, updateFirstName, updateLastName;

    //<editor-fold defaultstate="collapsed" desc="getter/setter">
    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    public Collection<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Collection<Department> departments) {
        this.departments = departments;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Department getSelectedDepartment() {
        return selectedDepartment;
    }

    public void setSelectedDepartment(Department selectedDepartment) {
        this.selectedDepartment = selectedDepartment;
    }

    public long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(long updateId) {
        this.updateId = updateId;
    }

    public long getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(long deleteId) {
        this.deleteId = deleteId;
    }

    public String getSaveFirstName() {
        return saveFirstName;
    }

    public void setSaveFirstName(String saveFirstName) {
        this.saveFirstName = saveFirstName;
    }

    public String getSaveLastName() {
        return saveLastName;
    }

    public void setSaveLastName(String saveLastName) {
        this.saveLastName = saveLastName;
    }

    public String getUpdateFirstName() {
        return updateFirstName;
    }

    public void setUpdateFirstName(String updateFirstName) {
        this.updateFirstName = updateFirstName;
    }

    public String getUpdateLastName() {
        return updateLastName;
    }

    public void setUpdateLastName(String updateLastName) {
        this.updateLastName = updateLastName;
    }
    //</editor-fold>

    @PostConstruct
    public void init() {
        L.debug("[" + this.getClass().getSimpleName() + "] : init() called");
        refreshView();
    }

    public void saveEmployee() {
        L.debug("[" + this.getClass().getSimpleName() + "] : saveEmployee() called");
        Employee employee = new Employee();
        employee.setFirstName(saveFirstName);
        employee.setLastName(saveLastName);
        employee.setDepartment(selectedDepartment);
        employeeService.saveEmployee(employee);
        refreshView();
    }

    public void updateEmployee() {
        L.debug("[" + this.getClass().getSimpleName() + "] : updateEmployee() called");
        Employee employeeData = new Employee();
        employeeData.setFirstName(updateFirstName);
        employeeData.setLastName(updateLastName);
        employeeData.setDepartment(updateSelectedDepartment);
        employeeService.updateEmployee(updateId, employeeData);
        refreshView();
    }

    public void deleteEmployee() {
        L.debug("[" + this.getClass().getSimpleName() + "] : deleteEmployee() called");
        employeeService.deleteEmployee(deleteId);
        refreshView();
    }

//    public void departmentChangeListener(ValueChangeEvent event){
//        Object newValue = event.getNewValue();
//        
//    }
    private void refreshView() {
        L.debug("[" + this.getClass().getSimpleName() + "] : refresh() called");
        this.employees = employeeService.getAllEmployees();
        this.departments = departmentService.getAllDepartments();
    }

}
