package rsreu.microchad.service.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentProjectID implements Serializable {

    private Long department;
    private Long project;

    public DepartmentProjectID(){}

    public DepartmentProjectID(Long department, Long project){
        this.department = department;
        this.project = project;
    }
}
