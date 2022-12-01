package rsreu.microchad.service.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
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
