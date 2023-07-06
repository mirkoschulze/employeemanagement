package de.netgain.employeemanagementapplication.service;

import de.netgain.employeemanagementapplication.model.Department;
import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.repository.DepartmentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation of {@link DepartmentService}.
 *
 * @author MirkoSchulze
 */
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger L = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        L.debug("[{}]] : getAllDepartments() called", this.getClass().getSimpleName());
        List<Department> departments = departmentRepository.findAll();
        L.debug("[{}] : returning List<Department> departments = {}", this.getClass().getSimpleName(), departments.toString());
        return departments;
    }

    @Override
    public Optional<Department> getDepartmentById(long id) {
        L.debug("[{}] : getDepartmentById(long id) called with param = {}", this.getClass().getSimpleName(), id);
        Optional<Department> department = departmentRepository.findById(id);
        L.debug("[{}] : returning Optional<Department> department = {}", this.getClass().getSimpleName(), department);
        return department;
    }

    @Override
    public Department saveDepartment(Department department) {
        L.debug("[{}] : saveDepartment(Department department) called with param = {}", this.getClass().getSimpleName(), department);
        return departmentRepository.save(department);
    }

    @Override
    public Optional<Department> updateDepartment(long id, Department departmentData) {
        L.debug("[{}] : updateDepartment(long id, Department departmentData) called with param = {}; {}", this.getClass().getSimpleName(), id, departmentData);
        Optional<Department> department = departmentRepository.findById(id);
        department.ifPresentOrElse(d -> {
            if (departmentData.getName() != null && !departmentData.getName().isBlank()) {
                d.setName(departmentData.getName());
            }
            departmentRepository.save(d);
            L.debug("[{}] : department updated to {}", this.getClass().getSimpleName(), d.toString());
        }, () -> L.debug("[{}] : department not updated", this.getClass().getSimpleName()));
        L.debug("[{}] : returning Optional<Department> Department = {}", this.getClass().getSimpleName(), department);
        return department;
    }

    @Override
    public void deleteDepartment(long id) {
        L.debug("[{}] : deleteDepartment(long id) called with param = {}", this.getClass().getSimpleName(), id);
        Optional<Department> department = departmentRepository.findById(id);
        department.ifPresentOrElse(d -> {
            List<Employee> employees = new ArrayList<>();
            employees.addAll(d.getEmployees());
            employees.forEach(e -> {
                d.removeEmployee(e);
            });
            departmentRepository.deleteById(id);
            L.debug("[{}] : department deleted", this.getClass().getSimpleName());
        }, () -> L.debug("[{}] : department not deleted", this.getClass().getSimpleName()));
    }
}
