package de.netgain.employeemanagementapplication.controller;

import de.netgain.employeemanagementapplication.Utils;
import de.netgain.employeemanagementapplication.model.Department;
import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.service.DepartmentService;
import de.netgain.employeemanagementapplication.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * MVC controller class, contains the logic for the GUI.
 *
 * @author MirkoSchulze
 */
@Component(value = "jsfController")
@Scope(value = "view")
public class JsfController implements Serializable {

    private static final Logger L = LoggerFactory.getLogger(JsfController.class);

    private static final String SUMMARY = "Name nicht zulässig";
    private static final String DETAIL = "Geben Sie einen gültigen Namen an.\n(Ein Großbuchstabe gefolgt von Kleinbuchstaben)";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    private List<Employee> employees;

    private List<Department> departments;

    private Department saveDepartment, updateDepartment;

    private Long updateEmployeeId, deleteEmployeeId, updateDepartmentId, deleteDepartmentId;

    private String saveFirstName, saveLastName, updateFirstName, updateLastName, saveName, updateName;

    //<editor-fold defaultstate="collapsed" desc="getter/setter">
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Department getSaveDepartment() {
        return saveDepartment;
    }

    public void setSaveDepartment(Department saveDepartment) {
        this.saveDepartment = saveDepartment;
    }

    public Department getUpdateDepartment() {
        return updateDepartment;
    }

    public void setUpdateDepartment(Department updateDepartment) {
        this.updateDepartment = updateDepartment;
    }

    public Long getUpdateEmployeeId() {
        return updateEmployeeId;
    }

    public void setUpdateEmployeeId(Long updateEmployeeId) {
        this.updateEmployeeId = updateEmployeeId;
    }

    public Long getDeleteEmployeeId() {
        return deleteEmployeeId;
    }

    public void setDeleteEmployeeId(Long deleteEmployeeId) {
        this.deleteEmployeeId = deleteEmployeeId;
    }

    public Long getUpdateDepartmentId() {
        return updateDepartmentId;
    }

    public void setUpdateDepartmentId(Long updateDepartmentId) {
        this.updateDepartmentId = updateDepartmentId;
    }

    public Long getDeleteDepartmentId() {
        return deleteDepartmentId;
    }

    public void setDeleteDepartmentId(Long deleteDepartmentId) {
        this.deleteDepartmentId = deleteDepartmentId;
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

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }
    //</editor-fold>

    @PostConstruct
    public void init() {
        L.debug("[{}] : init() called", this.getClass().getSimpleName());
        reloadLists();
    }

    /**
     * Persist an {@link Employee} into the database.
     */
    public void saveEmployee() {
        L.debug("[{}] : saveEmployee() called", this.getClass().getSimpleName());
        if (Utils.validateName(saveFirstName) && Utils.validateName(saveLastName)) {
            Employee employee = new Employee();
            employee.setFirstName(saveFirstName);
            employee.setLastName(saveLastName);
            employee.setDepartment(saveDepartment);
            employeeService.saveEmployee(employee);
            reloadLists();
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, SUMMARY, DETAIL);
        }
    }

    /**
     * Update an {@link Employee} in the database.
     */
    public void updateEmployee() {
        L.debug("[{}] : updateEmployee() called", this.getClass().getSimpleName());
        if (Utils.validateName(updateFirstName) && Utils.validateName(updateLastName)) {
            Employee employeeData = new Employee();
            employeeData.setFirstName(updateFirstName);
            employeeData.setLastName(updateLastName);
            employeeData.setDepartment(updateDepartment);
            employeeService.updateEmployee(updateEmployeeId, employeeData);
            reloadLists();
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, SUMMARY, DETAIL);
        }
    }

    /**
     * Delete an {@link Employee} from the database.
     */
    public void deleteEmployee() {
        L.debug("[{}] : deleteEmployee() called", this.getClass().getSimpleName());
        employeeService.deleteEmployee(deleteEmployeeId);
        reloadLists();
    }

    /**
     * Persist a {@link Department} into the database.
     */
    public void saveDepartment() {
        L.debug("[{}] : saveDepartment() called", this.getClass().getSimpleName());
        if (Utils.validateName(saveName)) {
            Department department = new Department(saveName);
            departmentService.saveDepartment(department);
            reloadLists();
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, SUMMARY, DETAIL);
        }
    }

    /**
     * Update a {@link Department} in the database.
     */
    public void updateDepartment() {
        L.debug("[{}] : updateDepartment() called", this.getClass().getSimpleName());
        Department departmentData = new Department(updateName);
        if (Utils.validateName(updateName)) {
            departmentService.updateDepartment(updateDepartmentId, departmentData);
            reloadLists();
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, SUMMARY, DETAIL);
        }
    }

    /**
     * Delete a {@link Department} from the database.
     */
    public void deleteDepartment() {
        L.debug("[{}] : deleteDepartment() called", this.getClass().getSimpleName());
        departmentService.deleteDepartment(deleteDepartmentId);
        reloadLists();
    }

    /**
     * Shows a new pop up message in the GUI.
     *
     * @param severity The severity level of the message
     * @param summary A summary of the reason to show the message
     * @param detail A detailed debug of the reason to show the message
     */
    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        L.debug("[{}] : addMessage(FacesMessage.Severity severity, String summary, String detail) called with param = {}, {}, {}",
                this.getClass().getSimpleName(), severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    /**
     * Reload the lists used for the datatables in the GUI.
     */
    private void reloadLists() {
        L.debug("[{}] : reloadLists() called", this.getClass().getSimpleName());
        this.employees = employeeService.getAllEmployees();
        this.departments = departmentService.getAllDepartments();
    }

}
