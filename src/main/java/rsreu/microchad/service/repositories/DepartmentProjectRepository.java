package rsreu.microchad.service.repositories;

import org.springframework.data.repository.CrudRepository;
import rsreu.microchad.service.entities.DepartmentProject;
import rsreu.microchad.service.entities.DepartmentProjectID;

import java.util.List;

public interface DepartmentProjectRepository extends CrudRepository<DepartmentProject, DepartmentProjectID> {

    List<DepartmentProject> findByDepartment(Long department);
    List<DepartmentProject> findByProject(Long project);

}
