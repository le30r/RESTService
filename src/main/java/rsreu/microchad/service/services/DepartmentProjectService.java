package rsreu.microchad.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rsreu.microchad.service.dto.DepartmentProjectDto;
import rsreu.microchad.service.dto.EmployeeRoleDto;
import rsreu.microchad.service.entities.Department;
import rsreu.microchad.service.entities.DepartmentProject;
import rsreu.microchad.service.entities.Project;
import rsreu.microchad.service.repositories.DepartmentProjectRepository;
import rsreu.microchad.service.repositories.DepartmentRepository;
import rsreu.microchad.service.repositories.ProjectRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DepartmentProjectService {

    @Autowired
    private DepartmentProjectRepository repository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public boolean save(DepartmentProjectDto dto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(dto.getDepartment());
        Optional<Project> optionalProject = projectRepository.findById(dto.getProject());
        if (optionalProject.isPresent() && optionalDepartment.isPresent()) {
            repository.save(DepartmentProject.builder()
                    .department(optionalDepartment.get())
                    .project(optionalProject.get())
                    .build());
            return true;
        }
        throw new NoSuchElementException();
    }

    public boolean delete(Long department, Long project) {
        Optional<Department> optionalDepartment = departmentRepository.findById(department);
        Optional<Project> optionalProject = projectRepository.findById(project);
        if (optionalProject.isPresent() && optionalDepartment.isPresent()) {
            repository.delete(DepartmentProject.builder()
                    .department(optionalDepartment.get())
                    .project(optionalProject.get())
                    .build());
            return true;
        }
        throw new NoSuchElementException();
    }

    public List<DepartmentProjectDto> findByDepartment(Long department) {
        return repository.findByDepartment(department).stream().map(DepartmentProjectDto::toModel).collect(Collectors.toList());
    }

    public List<DepartmentProjectDto> findByProject(Long project) {
        return repository.findByProject(project).stream().map(DepartmentProjectDto::toModel).collect(Collectors.toList());
    }

    public List<DepartmentProjectDto> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(DepartmentProjectDto::toModel)
                .collect(Collectors.toList());
    }




}
