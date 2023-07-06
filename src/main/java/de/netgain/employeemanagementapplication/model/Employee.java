package de.netgain.employeemanagementapplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import org.hibernate.annotations.OptimisticLocking;

/**
 * Representation of company employee.
 *
 * @author MirkoSchulze
 */
@Entity
@Table(name = "employees")
@OptimisticLocking
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Department department;
    @Embedded
    @AttributeOverride(name = "houseNumber", column = @Column(name = " house_number"))
    private Address address;

    public Employee() {
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(String firstName, String lastName, Department department) {
        this(firstName, lastName);
        this.setDepartment(department);
    }

    public Employee(String firstName, String lastName, Department department, Address address) {
        this(firstName, lastName, department);
        this.address = address;
    }

    //<editor-fold defaultstate="collapsed" desc="getter/setter">
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        if (this.department == department) {
            return;
        }
        if (this.department != null) {
            this.department.getEmployees().remove(this);
        }
        if (department != null) {
            department.getEmployees().add(this);
        }
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    //</editor-fold>
    //TODO address
    //<editor-fold defaultstate="collapsed" desc="toString()/hashCode()/equals()">
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("%s[id=%d] %s %s", this.getClass().getSimpleName(),
                this.getId(), this.getFirstName(), this.getLastName()));
        if (this.department != null) {
            sb.append(", ");
            sb.append(this.getDepartment().toString());
        }
        if (this.address != null) {
            sb.append(", ");
            sb.append(this.address.toString());
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.firstName);
        hash = 59 * hash + Objects.hashCode(this.lastName);
        hash = 59 * hash + Objects.hashCode(this.department);
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
        final Employee other = (Employee) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return Objects.equals(this.department, other.department);
    }
    //</editor-fold>

}
