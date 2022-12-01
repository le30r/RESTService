package rsreu.microchad.service.dto;

public class DepartmentProjectDto {
    private Long department;
    private Long project;

    public static DepartmentProjectDto toModel(DepartmentProject entity) {
        DepartmentProjectDto dto = new DepartmentProjectDto();
        dto.setDepartment(entity.getDepartment());
        dto.setProject(entity.getProject());
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
