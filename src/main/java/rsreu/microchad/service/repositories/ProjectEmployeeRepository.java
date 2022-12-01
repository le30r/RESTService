package rsreu.microchad.service.repositories;

import org.springframework.data.repository.CrudRepository;
import rsreu.microchad.service.entities.ProjectEmployee;
import rsreu.microchad.service.entities.ProjectEmployeeID;

public interface ProjectEmployeeRepository extends CrudRepository<ProjectEmployee, ProjectEmployeeID> {
}
