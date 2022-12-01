package rsreu.microchad.service.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProjectEmployeeID implements Serializable {
    private Long project;
    private Long employee;

    public ProjectEmployeeID(){}

    public ProjectEmployeeID(Long project, Long employee){
        this.project = project;
        this.employee = employee;
    }
}
