package rsreu.microchad.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import rsreu.microchad.service.entities.Project;

@Builder
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
