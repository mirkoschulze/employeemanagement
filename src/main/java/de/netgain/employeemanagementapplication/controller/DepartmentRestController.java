package de.netgain.employeemanagementapplication.controller;

import de.netgain.employeemanagementapplication.model.Department;
import de.netgain.employeemanagementapplication.service.DepartmentService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contains logic to execute JPA operations with {@link Department}s via REST.
 *
 * @author MirkoSchulze
 */
@RestController
@RequestMapping(value = "/departments")
public class DepartmentRestController {
    
    private static final Logger L = LoggerFactory.getLogger(DepartmentRestController.class);

    @Autowired
    private DepartmentService departmentService;

    /**
     * Get all {@link Department} entities.
     *
     * @return A List containing all found Departments.
     */
    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<Department> readDepartments() {
        L.debug("[" + this.getClass().getSimpleName() + "] : readDepartments() called");
        return departmentService.getAllDepartments();
    }

    /**
     * Get the {@link Department} with the submitted id.
     *
     * @param id Id of the requested Department.
     * @return The requested Department.
     *
     */
    @GetMapping(value = "/{depId}")
    public Department readDepartment(@PathVariable(value = "depId") long id) {
        L.debug("[" + this.getClass().getSimpleName() + "] : readDepartment(long id) called with param = " + id);
        return departmentService.getDepartmentById(id).get();
    }

    /**
     * Submit a {@link Department} entity to persist.
     *
     * @param department The Department to persist.
     * @return The persisted Department.
     */
    @PostMapping
    public Department saveDepartment(@RequestBody Department department) {
        L.debug("[" + this.getClass().getSimpleName() + "] : saveDepartment(Department department) called with param = " + department);
        return departmentService.saveDepartment(department);
    }

    /**
     * Submit a {@link Department} entity to merge it with one in the database.
     *
     * @param id Id of the Department to be merged.
     * @param departmentData Department with new data
     * @return The merged Department.
     */
    @PutMapping(value = "/{depId}")
    public Department updateDepartment(@PathVariable(value = "depId") long id, @RequestBody Department departmentData) {
        L.debug("[" + this.getClass().getSimpleName() + "] : updateDepartmen(long id, Department departmentData) called with param = " + id + ", " + departmentData);
        return departmentService.updateDepartment(id, departmentData).get();
    }

    /**
     * Delete a {@link Department} entity from the database.
     *
     * @param id Id of the Department to be deleted.
     */
    @DeleteMapping(value = "/{depId}")
    public void deleteDepartment(@PathVariable(value = "depId") long id) {
        L.debug("[" + this.getClass().getSimpleName() + "] : deleteDepartment(long id) called with param = " + id);
        departmentService.deleteDepartment(id);
    }
    
}
