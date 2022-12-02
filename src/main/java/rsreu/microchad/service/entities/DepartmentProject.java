package rsreu.microchad.service.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department_project")
@IdClass(DepartmentProjectID.class)
public class DepartmentProject {
    @Id
    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    @Id
    @ManyToOne
    @JoinColumn(name = "project")
    private Project project;

    public Department getDepartment() {
        return department;
    }

    public void setEmployee(Department department) {
        this.department = department;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
