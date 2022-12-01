package rsreu.microchad.service.entities;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<EmployeeRole> node;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<DepartmentProject> projects;

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


    public List<EmployeeRole> getNode() {
        return node;
    }

    public void setNode(List<EmployeeRole> node) {
        this.node = node;
    }

}
