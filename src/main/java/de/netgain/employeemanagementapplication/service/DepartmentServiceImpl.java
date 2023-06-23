package de.netgain.employeemanagementapplication.service;

import de.netgain.employeemanagementapplication.model.Department;
import de.netgain.employeemanagementapplication.repository.DepartmentRepository;
import java.util.Collection;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentServiceImpl implements DepartmentService{
    
    private static final Logger L = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Collection<Department> getAllDepartments() {
        L.debug("[" + this.getClass().getSimpleName() + "] : getAllDepartments() called");
        Collection<Department> departments = departmentRepository.findAll();
        L.debug("[" + this.getClass().getSimpleName() + "] : returning Collection<Department> departments = " + departments.toString());
        return departments;
    }

    @Override
    public Optional<Department> getDepartmentById(long id) {
        L.debug("[" + this.getClass().getSimpleName() + "] : getDepartmentById(long id) called with param = " + id);
        Optional<Department> department = departmentRepository.findById(id);
        L.debug("[" + this.getClass().getSimpleName() + "] : returning Optional<Department> department = " + department.toString());
        return department;
    }

    @Override
    public Department saveDepartment(Department department) {
        L.debug("[" + this.getClass().getSimpleName() + "] : saveDepartment(Department department) called with param = " + department.toSimpleName());
        return departmentRepository.save(department);
    }

    @Override
    public Optional<Department> updateDepartment(long id, Department departmentData) {
        L.debug("[" + this.getClass().getSimpleName() + "] : updateDepartment(long id, Department departmentData) called with param = " + id + "; " + departmentData.toSimpleName());
        Optional<Department> Department = departmentRepository.findById(id);
        Department.ifPresentOrElse(d -> {
            d.setName(departmentData.getName());
            departmentRepository.save(d);
            L.debug("[" + this.getClass().getSimpleName() + "] : Department updated");
        }, () -> L.debug("[" + this.getClass().getSimpleName() + "] : Department not updated"));
        L.debug("[" + this.getClass().getSimpleName() + "] : returning Optional<Department> Department = " + Department.toString());
        return Department;
    }

    @Override
    public void deleteDepartment(long id) {
        L.debug("[" + this.getClass().getSimpleName() + "] : deleteDepartment(long id) called with param = " + id);
        Optional<Department> department = departmentRepository.findById(id);
        department.ifPresent(d -> d.getEmployees().forEach(e -> d.removeEmployee(e)));
        departmentRepository.deleteById(id);
    }
}
