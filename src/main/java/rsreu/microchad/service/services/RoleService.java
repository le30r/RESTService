package rsreu.microchad.service.services;

import liquibase.repackaged.org.apache.commons.lang3.NotImplementedException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rsreu.microchad.service.dto.DepartmentDto;
import rsreu.microchad.service.dto.DepartmentProjectDto;
import rsreu.microchad.service.dto.RoleDto;
import rsreu.microchad.service.entities.Department;
import rsreu.microchad.service.entities.Role;
import rsreu.microchad.service.repositories.DepartmentRepository;
import rsreu.microchad.service.repositories.RoleRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RoleService {


    @Autowired
    private RoleRepository repository;

    public RoleDto findById(Long id) {
        Optional<Role> optional = repository.findById(id);
        if (optional.isPresent()) {
            return RoleDto.toModel(optional.get());
        }
        throw new NoSuchElementException();
    }

    public List<RoleDto> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(RoleDto::toModel)
                .collect(Collectors.toList());
    }

    public boolean save(RoleDto dto) {
        Optional<Role> department = repository.findById(dto.getId());
        if(department.isEmpty()) {
            repository.save(Role.builder()
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

    public boolean update(RoleDto dto) {
        Optional<Role> optional = repository.findById(dto.getId());
        if(optional.isPresent()) {
            Role department = optional.get();
            department.setName(dto.getName());
            return true;
        }
        return false;
    }
}
