package rsreu.microchad.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import rsreu.microchad.service.entities.Project;

@Builder
@AllArgsConstructor
@Schema(name = "Информация о проекте")
public class ProjectDto {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "Название проекта")
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
