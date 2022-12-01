package rsreu.microchad.service.repositories;

import org.springframework.data.repository.CrudRepository;
import rsreu.microchad.service.entities.DepartmentProject;
import rsreu.microchad.service.entities.DepartmentProjectID;

public interface DepartmentProjectRepository extends CrudRepository<DepartmentProject, DepartmentProjectID> {
}
