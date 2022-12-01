package rsreu.microchad.service.repositories;

import org.springframework.data.repository.CrudRepository;
import rsreu.microchad.service.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
