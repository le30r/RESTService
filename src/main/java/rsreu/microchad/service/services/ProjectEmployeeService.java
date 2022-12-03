package rsreu.microchad.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rsreu.microchad.service.dto.EmployeeDto;
import rsreu.microchad.service.dto.ProjectEmployeeDto;
import rsreu.microchad.service.entities.Employee;
import rsreu.microchad.service.entities.Project;
import rsreu.microchad.service.entities.ProjectEmployee;
import rsreu.microchad.service.repositories.EmployeeRepository;
import rsreu.microchad.service.repositories.ProjectEmployeeRepository;
import rsreu.microchad.service.repositories.ProjectRepository;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectEmployeeService {

    @Autowired
    private ProjectEmployeeRepository repository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean save(ProjectEmployeeDto dto) {
        Optional<Employee> employeeOptional = employeeRepository.findById(dto.getEmployee());
        Optional<Project> projectOptional = projectRepository.findById(dto.getProject());
        if (employeeOptional.isPresent() && projectOptional.isPresent()) {
            repository.save(ProjectEmployee.builder()
                    .employee(employeeOptional.get())
                    .project(projectOptional.get()).build());
            return true;
        }
        throw new NoSuchElementException();
    }


    @Transactional
    public boolean delete(Long employee, Long project) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employee);
        Optional<Project> projectOptional = projectRepository.findById(project);
        if (employeeOptional.isPresent() && projectOptional.isPresent()) {
            repository.delete(ProjectEmployee.builder()
                    .employee(employeeOptional.get())
                    .project(projectOptional.get())
                    .build());
            return true;
        }
        throw new NoSuchElementException();
    }

    public List<ProjectEmployeeDto> findByEmployee(Long employee) {
        return repository.findByEmployee(employee).stream().map(ProjectEmployeeDto::toModel).collect(Collectors.toList());
    }

    public List<ProjectEmployeeDto> findByProject(Long project) {
        return repository.findByProject(project).stream().map(ProjectEmployeeDto::toModel).collect(Collectors.toList());
    }

    public List<EmployeeDto> findAllEmployees(Long project) {
       return repository.findByProject(project)
                .stream()
                .map(ProjectEmployee::getEmployee)
                .map(EmployeeDto::toModel)
                .toList();

    }

    public List<ProjectEmployeeDto> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(ProjectEmployeeDto::toModel)
                .collect(Collectors.toList());
    }
}
