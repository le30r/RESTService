package rsreu.microchad.service.entities;


import javax.persistence.*;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects_employee")
@IdClass(ProjectEmployeeID.class)
public class ProjectEmployee {
    @Id
    @ManyToOne
    @JoinColumn(name = "project")
    private Project project;

    @Id
    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
