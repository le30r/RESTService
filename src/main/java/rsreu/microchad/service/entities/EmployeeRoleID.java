package rsreu.microchad.service.entities;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class EmployeeRoleID implements Serializable {
    private Long role;
    private Long employee;
    private Long department;

    public EmployeeRoleID(){}

    public EmployeeRoleID(Long role, Long employee, Long department){
        this.role = role;
        this.employee = employee;
        this.department =department;
    }
}
