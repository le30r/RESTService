package rsreu.microchad.service.repositories;

import org.springframework.data.repository.CrudRepository;
import rsreu.microchad.service.entities.EmployeeRole;
import rsreu.microchad.service.entities.EmployeeRoleID;

import java.util.List;

public interface EmployeeRoleRepository extends CrudRepository<EmployeeRole, EmployeeRoleID> {
    List<EmployeeRole> findByDepartment(Long department);
    List<EmployeeRole> findByRole(Long role);
    List<EmployeeRole> findByEmployee(Long employee);
}
