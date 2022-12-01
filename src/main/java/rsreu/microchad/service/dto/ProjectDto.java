package rsreu.microchad.service.dto;

public class ProjectDto {
    private Long id;
    private Long name;

    public static ProjectDto toModel (Project entity) {
        ProjectDto dto = new ProjectDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public ProjectDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }
}
