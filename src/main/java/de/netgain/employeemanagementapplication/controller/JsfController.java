package de.netgain.employeemanagementapplication.controller;

import de.netgain.employeemanagementapplication.Utils;
import de.netgain.employeemanagementapplication.model.Department;
import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.service.DepartmentService;
import de.netgain.employeemanagementapplication.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * MVC controller class, contains the logic for the GUI.
 *
 * @author MirkoSchulze
 */
@Named("jsfController")
@ViewScoped
public class JsfController implements Serializable {

    private static final Logger L = LoggerFactory.getLogger(JsfController.class);

    private static final String SUMMARY = "Name nicht zulässig";
    private static final String DETAIL = "Geben Sie einen gültigen Namen an.\n\n(Ein Großbuchstabe gefolgt von Kleinbuchstaben)";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    private List<Employee> employees;

    private List<Department> departments;

    private Department saveDepartment, updateDepartment;

    private long updateEmployeeId, deleteEmployeeId, updateDepartmentId, deleteDepartmentId;

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

    public long getUpdateEmployeeId() {
        return updateEmployeeId;
    }

    public void setUpdateEmployeeId(long updateEmployeeId) {
        this.updateEmployeeId = updateEmployeeId;
    }

    public long getDeleteEmployeeId() {
        return deleteEmployeeId;
    }

    public void setDeleteEmployeeId(long deleteEmployeeId) {
        this.deleteEmployeeId = deleteEmployeeId;
    }

    public long getUpdateDepartmentId() {
        return updateDepartmentId;
    }

    public void setUpdateDepartmentId(long updateDepartmentId) {
        this.updateDepartmentId = updateDepartmentId;
    }

    public long getDeleteDepartmentId() {
        return deleteDepartmentId;
    }

    public void setDeleteDepartmentId(long deleteDepartmentId) {
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
        L.info("[" + this.getClass().getSimpleName() + "] : init() called");
        reloadLists();
    }

    /**
     * Persist an {@link Employee} to the database.
     */
    public void saveEmployee() {
        L.info("[" + this.getClass().getSimpleName() + "] : saveEmployee() called");
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
     * Persist an {@link Employee} in the database.
     */
    public void updateEmployee() {
        L.info("[" + this.getClass().getSimpleName() + "] : updateEmployee() called");
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
        L.info("[" + this.getClass().getSimpleName() + "] : deleteEmployee() called");
        employeeService.deleteEmployee(deleteEmployeeId);
        reloadLists();
    }

    /**
     * Persist a {@link Department} to the database.
     */
    public void saveDepartment() {
        L.info("[" + this.getClass().getSimpleName() + "] : saveDepartment() called");
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
        L.info("[" + this.getClass().getSimpleName() + "] : updateDepartment() called");
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
        L.info("[" + this.getClass().getSimpleName() + "] : deleteDepartment() called");
        departmentService.deleteDepartment(deleteDepartmentId);
        reloadLists();
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    /**
     * Reload the lists used for the datatables in the GUI.
     */
    private void reloadLists() {
        L.info("[" + this.getClass().getSimpleName() + "] : reloadLists() called");
        this.employees = employeeService.getAllEmployees();
        this.departments = departmentService.getAllDepartments();
    }

}
