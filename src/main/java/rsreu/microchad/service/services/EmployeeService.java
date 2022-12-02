package rsreu.microchad.service.services;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import rsreu.microchad.service.dto.EmployeeDto;
import rsreu.microchad.service.entities.Employee;
import rsreu.microchad.service.repositories.EmployeeRepository;
import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public boolean save(EmployeeDto dto) {
        if (repository.findById(dto.getId()).isEmpty()) {
            Employee employee = Employee.builder()
                    .name(dto.getName())
                    .middleName(dto.getMiddleName())
                    .lastName(dto.getLastName())
                    .birthday(new Date(dto.getBirthdate().getTime()))
                    .build();
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    public EmployeeDto findById(Long id) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isPresent()) {
            return EmployeeDto.toModel(employee.get());
        }
        throw new NoSuchElementException();
    }


    @Transactional
    public List<EmployeeDto> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(EmployeeDto::toModel)
                .collect(Collectors.toList());
    }


    @Transactional
    public boolean update(EmployeeDto dto) {
        Optional<Employee> employee = repository.findById(dto.getId());
        if (employee.isPresent()) {
            Employee entity = employee.get();
            entity.setName(dto.getName());
            entity.setMiddleName(dto.getMiddleName());
            entity.setLastName(dto.getLastName());
            entity.setBirthday(new Date(dto.getBirthdate().getTime()));
            repository.save(entity);
            return true;
        } else {
            throw new NoSuchElementException();
        }
    }

}