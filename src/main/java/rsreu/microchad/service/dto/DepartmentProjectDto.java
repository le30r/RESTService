package rsreu.microchad.service.dto;

import rsreu.microchad.service.entities.DepartmentProject;

public class DepartmentProjectDto {
    private Long department;
    private Long project;

    public static DepartmentProjectDto toModel(DepartmentProject entity) {
        DepartmentProjectDto dto = new DepartmentProjectDto();
        dto.setDepartment(entity.getDepartment().getId());
        dto.setProject(entity.getProject().getId());
        return dto;
    }

    public DepartmentProjectDto() {
    }

    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }

    public Long getProject() {
        return project;
    }

    public void setProject(Long project) {
        this.project = project;
    }
}
