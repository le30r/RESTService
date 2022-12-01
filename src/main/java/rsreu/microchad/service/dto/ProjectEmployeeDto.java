package rsreu.microchad.service.dto;

import rsreu.microchad.service.entities.ProjectEmployee;

public class ProjectEmployeeDto {
    private Long project;
    private Long employee;
    private Long role;

    public static ProjectEmployeeDto toModel(ProjectEmployee entity) {
        ProjectEmployeeDto dto = new ProjectEmployeeDto();
        dto.setEmployee(entity.getEmployee().getId());
        dto.setProject(entity.getProject().getId());
        return dto;
    }

    public ProjectEmployeeDto() {
    }

    public Long getProject() {
        return project;
    }

    public void setProject(Long project) {
        this.project = project;
    }

    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(Long employee) {
        this.employee = employee;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }
}
