package rsreu.microchad.service.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rsreu.microchad.service.dto.DepartmentDto;
import rsreu.microchad.service.dto.ProjectDto;
import rsreu.microchad.service.entities.Employee;
import rsreu.microchad.service.entities.Project;
import rsreu.microchad.service.repositories.EmployeeRepository;
import rsreu.microchad.service.repositories.ProjectRepository;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    public boolean save(ProjectDto dto) {
        if (repository.findById(dto.getId()).isEmpty()) {
            Project project = Project.builder()
                    .name(dto.getName())
                    .build();
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    public boolean update(ProjectDto dto) {
        Optional<Project> project = repository.findById(dto.getId());
        if (project.isPresent()) {
            Project entity = project.get();
            entity.setName(dto.getName());
            repository.save(entity);
        }
        else {
            throw new NoSuchElementException();
        }
        return true;
    }

    public ProjectDto findById(Long id) {
        Optional<Project> project = repository.findById(id);
        if (project.isPresent()) {
            return ProjectDto.toModel(project.get());
        }
        throw new NoSuchElementException();
    }

    public List<ProjectDto> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(ProjectDto::toModel)
                .collect(Collectors.toList());
    }
}
