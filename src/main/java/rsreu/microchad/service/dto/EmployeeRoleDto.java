package rsreu.microchad.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import rsreu.microchad.service.entities.EmployeeRole;

@Builder
@AllArgsConstructor
public class EmployeeRoleDto {
    private Long role;
    private Long employee;
    private Long department;

    public static EmployeeRoleDto toModel(EmployeeRole entity) {
        EmployeeRoleDto dto = new EmployeeRoleDto();
        dto.setEmployee(entity.getEmployee().getId());
        dto.setDepartment(entity.getDepartment().getId());
        dto.setRole(entity.getRole().getId());
        return dto;
    }

    public EmployeeRoleDto() {
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(Long employee) {
        this.employee = employee;
    }

    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }
}
