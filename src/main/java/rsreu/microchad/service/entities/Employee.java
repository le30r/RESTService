package rsreu.microchad.service.entities;

import jakarta.persistence.*;
import liquibase.datatype.core.DateType;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "middlename")
    private String middleName;

    @Column
    private String lastName;

    @Column(name = "birthdate")
    private Date birthday;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<EmployeeRole> node;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<ProjectEmployee> projects;


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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<EmployeeRole> getNode() {
        return node;
    }

    public void setNode(List<EmployeeRole> node) {
        this.node = node;
    }
}
