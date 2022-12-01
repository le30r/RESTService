package rsreu.microchad.service.dto;

public class EmployeeRoleDto {
    private Long role;
    private Long employee;
    private Long department;

    public static EmployeeRoleDto toModel(EmployeeRole entity) {
        EmployeeRoleDto dto = new EmployeeRoleDto();
        dto.setEmployee(entity.getEmployee());
        dto.setDepartment(entity.getDepartment());
        dto.setRole(entity.getRole());
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
