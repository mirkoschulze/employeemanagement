package de.netgain.employeemanagementapplication.service;

import de.netgain.employeemanagementapplication.model.Department;
import de.netgain.employeemanagementapplication.repository.DepartmentRepository;
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
        L.info("[" + this.getClass().getSimpleName() + "] : getAllDepartments() called");
        List<Department> departments = departmentRepository.findAll();
        L.info("[" + this.getClass().getSimpleName() + "] : returning Collection<Department> departments = " + departments.toString());
        return departments;
    }

    @Override
    public Optional<Department> getDepartmentById(long id) {
        L.info("[" + this.getClass().getSimpleName() + "] : getDepartmentById(long id) called with param = " + id);
        Optional<Department> department = departmentRepository.findById(id);
        L.info("[" + this.getClass().getSimpleName() + "] : returning Optional<Department> department = " + department);
        return department;
    }

    @Override
    public Department saveDepartment(Department department) {
        L.info("[" + this.getClass().getSimpleName() + "] : saveDepartment(Department department) called with param = " + department);
        return departmentRepository.save(department);
    }

    @Override
    public Optional<Department> updateDepartment(long id, Department departmentData) {
        L.info("[" + this.getClass().getSimpleName() + "] : updateDepartment(long id, Department departmentData) called with param = " + id + "; " + departmentData);
        Optional<Department> department = departmentRepository.findById(id);
        department.ifPresentOrElse(d -> {
            if (!departmentData.getName().isBlank()) {
                d.setName(departmentData.getName());
            }
            departmentRepository.save(d);
            L.info("[" + this.getClass().getSimpleName() + "] : Department updated");
        }, () -> L.info("[" + this.getClass().getSimpleName() + "] : Department not updated"));
        L.info("[" + this.getClass().getSimpleName() + "] : returning Optional<Department> Department = " + department);
        return department;
    }

    @Override
    public void deleteDepartment(long id) {
        L.info("[" + this.getClass().getSimpleName() + "] : deleteDepartment(long id) called with param = " + id);
        Optional<Department> department = departmentRepository.findById(id);
        department.ifPresent(d -> d.getEmployees().forEach(e -> d.removeEmployee(e)));
        departmentRepository.deleteById(id);
    }
}
