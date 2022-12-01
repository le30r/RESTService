package rsreu.microchad.service.repositories;

import org.springframework.data.repository.CrudRepository;
import rsreu.microchad.service.entities.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
