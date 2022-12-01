package rsreu.microchad.service.repositories;

import org.springframework.data.repository.CrudRepository;
import rsreu.microchad.service.entities.EmployeeRole;
import rsreu.microchad.service.entities.EmployeeRoleID;

public interface EmployeeRoleRepository extends CrudRepository<EmployeeRole, EmployeeRoleID> {
}
