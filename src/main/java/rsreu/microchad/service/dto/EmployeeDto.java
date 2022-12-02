package rsreu.microchad.service.dto;

import lombok.Builder;
import rsreu.microchad.service.entities.Employee;

import java.util.Date;

@Builder
public class EmployeeDto {
    private Long id;
    private String name;
    private String middleName;
    private String lastName;
    private Date birthdate;

    public static EmployeeDto toModel(Employee entity) {
        return EmployeeDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .middleName(entity.getMiddleName())
                .lastName(entity.getLastName())
                .birthdate(entity.getBirthday())
                .build();
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
}
