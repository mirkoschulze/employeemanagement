package de.netgain.employeemanagementapplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.annotations.OptimisticLocking;

/**
 * Representation of a company department.
 *
 * @author MirkoSchulze
 */
@Entity
@Table(name = "departments")
@OptimisticLocking
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    private long id;
    @Column(name = "name")
    private String name;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(targetEntity = Employee.class, mappedBy = "department", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private final List<Employee> employees;
    @Embedded
    @AttributeOverride(name = "houseNumber", column = @Column(name = " house_number"))
    private Address address;

    public Department() {
        employees = new ArrayList<>();
    }

    public Department(String name) {
        this();
        this.name = name;
    }

    public Department(String name, Address address) {
        this(name);
        this.address = address;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        if (employees != null) {
            for (Employee employee : this.employees) {
                this.removeEmployee(employee);
            }
            for (Employee employee : employees) {
                this.addEmployee(employee);
            }
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    //</editor-fold>
    
    public void addEmployee(Employee employee) {
        if (employee == null) {
            return;
        }
        employee.setDepartment(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setDepartment(null);
    }

    //<editor-fold defaultstate="collapsed" desc="toString()/hashCode()/equals()">
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("%s[id=%d] %s", this.getClass().getSimpleName(),
                this.getId(), this.getName()));
        if(this.address != null)sb.append(", ").append(this.address.toString());
        return sb.toString();
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
