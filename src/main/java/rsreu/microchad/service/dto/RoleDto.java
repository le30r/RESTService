package rsreu.microchad.service.dto;

import rsreu.microchad.service.entities.Role;

public class RoleDto {
    private Long id;
    private String name;

    public static RoleDto toModel(Role entity) {
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public RoleDto() {
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
