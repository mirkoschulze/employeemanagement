package de.netgain.employeemanagementapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(targetEntity = Employee.class, mappedBy = "department")
    private final Collection<Employee> employees;

    public Department() {
        employees = new ArrayList<>();
    }

    //<editor-fold defaultstate="collapsed" desc="getter/setter">
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        if (employees != null) {
            for (Employee employee : this.employees) {
                this.removeEmployee(employee);
            }
            for (Employee employee : employees) {
                this.addEmployee(employee);
            }
        }
    }
    //</editor-fold>

    public void addEmployee(Employee employee) {
        if (employee == null) {
            return;
        }
        employee.setDepartment(this);
    }

    public void removeEmployee(Employee employee) {
        this.getEmployees().remove(employee);
        employee.setDepartment(null);
    }

    public String toSimpleName() {
        return "[" + id + "]" + name;
    }

    //<editor-fold defaultstate="collapsed" desc="toString()/hashCode()/equals()">
    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", employees=" + employees + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.employees);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Department other = (Department) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.employees, other.employees);
    }
    //</editor-fold>

}
