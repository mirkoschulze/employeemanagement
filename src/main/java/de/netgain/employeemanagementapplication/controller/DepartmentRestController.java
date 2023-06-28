package de.netgain.employeemanagementapplication.controller;

import de.netgain.employeemanagementapplication.model.Department;
import de.netgain.employeemanagementapplication.service.DepartmentService;
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
public class DepartmentRestController {
    
    private static final Logger L = LoggerFactory.getLogger(DepartmentRestController.class);

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public Collection<Department> readDepartments() {
        L.info("[" + this.getClass().getSimpleName() + "] : readDepartments() called");
        return departmentService.getAllDepartments();
    }

    @GetMapping(value = "/departments/{depId}")
    public Department readDepartment(@PathVariable(value = "depId") long id) {
        L.info("[" + this.getClass().getSimpleName() + "] : readDepartment(long id) called with param = " + id);
        return departmentService.getDepartmentById(id).get();
    }

    @PostMapping(value = "/departments")
    public Department saveDepartment(@RequestBody Department deployee) {
        L.info("[" + this.getClass().getSimpleName() + "] : saveDepartment(Department department) called with param = " + deployee);
        return departmentService.saveDepartment(deployee);
    }

    @PutMapping(value = "/departments/{depId}")
    public Department updateDepartment(@PathVariable(value = "depId") long id, @RequestBody Department deployeeData) {
        L.info("[" + this.getClass().getSimpleName() + "] : updateDepartmen(long id, Department departmentData) called with param = " + id + ", " + deployeeData);
        return departmentService.updateDepartment(id, deployeeData).get();
    }

    @DeleteMapping(value = "/departments/{depId}")
    public void deleteDepartment(@PathVariable(value = "depId") long id) {
        L.info("[" + this.getClass().getSimpleName() + "] : deleteDepartment(long id) called with param = " + id);
        departmentService.deleteDepartment(id);
    }

}
