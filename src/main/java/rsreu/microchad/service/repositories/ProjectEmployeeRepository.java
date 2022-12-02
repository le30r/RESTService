package rsreu.microchad.service.repositories;

import org.springframework.data.repository.CrudRepository;
import rsreu.microchad.service.entities.ProjectEmployee;
import rsreu.microchad.service.entities.ProjectEmployeeID;

import java.util.List;

public interface ProjectEmployeeRepository extends CrudRepository<ProjectEmployee, ProjectEmployeeID> {
    List<ProjectEmployee> findByProject(Long department);
    List<ProjectEmployee> findByEmployee(Long employee);
}
