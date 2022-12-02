package rsreu.microchad.service.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rsreu.microchad.service.dto.DepartmentProjectDto;
import rsreu.microchad.service.dto.EmployeeRoleDto;
import rsreu.microchad.service.dto.ProjectEmployeeDto;
import rsreu.microchad.service.entities.*;
import rsreu.microchad.service.repositories.DepartmentRepository;
import rsreu.microchad.service.repositories.EmployeeRepository;
import rsreu.microchad.service.repositories.EmployeeRoleRepository;
import rsreu.microchad.service.repositories.RoleRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeRoleService {

    @Autowired
    private EmployeeRoleRepository repository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    public boolean save(EmployeeRoleDto dto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(dto.getDepartment());
        Optional<Role> optionalRole = roleRepository.findById(dto.getRole());
        Optional<Employee> optionalEmployee = employeeRepository.findById(dto.getEmployee());
        if (optionalRole.isPresent() && optionalDepartment.isPresent() && optionalEmployee.isPresent()) {
            repository.save(EmployeeRole.builder()
                    .department(optionalDepartment.get())
                    .employee(optionalEmployee.get())
                    .role(optionalRole.get())
                    .build());
            return true;
        }
        throw new NoSuchElementException();
    }

    public boolean delete(Long department, Long project, Long employee) {
        Optional<Department> optionalDepartment = departmentRepository.findById(department);
        Optional<Role> optionalRole = roleRepository.findById(project);
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee);
        if (optionalRole.isPresent() && optionalDepartment.isPresent() && optionalEmployee.isPresent()) {
            repository.delete(EmployeeRole.builder()
                    .department(optionalDepartment.get())
                    .employee(optionalEmployee.get())
                    .role(optionalRole.get())
                    .build());
            return true;
        }
        throw new NoSuchElementException();
    }

    public List<EmployeeRoleDto> findByDepartment(Long department) {
        return repository.findByDepartment(department).stream().map(EmployeeRoleDto::toModel).collect(Collectors.toList());
    }

    public List<EmployeeRoleDto> findByEmployee(Long employee) {
        return repository.findByEmployee(employee).stream().map(EmployeeRoleDto::toModel).collect(Collectors.toList());
    }

    public List<EmployeeRoleDto> findByRole(Long role) {
        return repository.findByRole(role).stream().map(EmployeeRoleDto::toModel).collect(Collectors.toList());
    }

    public List<EmployeeRoleDto> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(EmployeeRoleDto::toModel)
                .collect(Collectors.toList());
    }


}
