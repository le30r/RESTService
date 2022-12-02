package rsreu.microchad.service.dto;

import lombok.Builder;
import rsreu.microchad.service.entities.Department;

@Builder
public class DepartmentDto {
    private Long id;
    private String name;

    public static DepartmentDto toModel(Department entity) {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public DepartmentDto() {
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
