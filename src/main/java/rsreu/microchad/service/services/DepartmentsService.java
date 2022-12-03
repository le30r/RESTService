package rsreu.microchad.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rsreu.microchad.service.dto.DepartmentDto;
import rsreu.microchad.service.entities.Department;
import rsreu.microchad.service.repositories.DepartmentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DepartmentsService {

    @Autowired
    private DepartmentRepository repository;

    public DepartmentDto findById(Long id) {
        Optional<Department> optional = repository.findById(id);
        if (optional.isPresent()) {
            return DepartmentDto.toModel(optional.get());
        }
        throw new NoSuchElementException();
    }

    public List<DepartmentDto> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(DepartmentDto::toModel)
                .collect(Collectors.toList());
    }

    public boolean save(DepartmentDto dto) {
        Optional<Department> department = repository.findById(dto.getId());
        if (department.isEmpty()) {
            repository.save(Department.builder()
                    .name(dto.getName())
                    .build());
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    public boolean update(DepartmentDto dto) {
        Optional<Department> optional = repository.findById(dto.getId());
        if (optional.isPresent()) {
            Department department = optional.get();
            department.setName(dto.getName());
            return true;
        }
        return false;
    }
}
