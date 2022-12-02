package rsreu.microchad.service.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(EmployeeRoleID.class)
public class EmployeeRole implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;

    @Id
    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

    @Id
    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Role role) {
        this.department = department;
    }
}
