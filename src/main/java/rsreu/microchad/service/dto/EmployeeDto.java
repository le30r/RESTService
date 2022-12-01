package rsreu.microchad.service.dto;

import rsreu.microchad.service.entities.Employee;

import java.util.Date;

public class EmployeeDto {
    private Long id;
    private String name;
    private String middleName;
    private String lastName;

    public static EmployeeDto toModel(Employee entity) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setLastName(entity.getLastName());
        return dto;
    }

    public EmployeeDto() {

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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    private Date birthdate;
}
