package rsreu.microchad.service.contollers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liquibase.repackaged.org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsreu.microchad.service.dto.EmployeeDto;
import rsreu.microchad.service.dto.EmployeeRoleDto;
import rsreu.microchad.service.dto.RoleDto;
import rsreu.microchad.service.repositories.EmployeeRoleRepository;
import rsreu.microchad.service.services.EmployeeRoleService;
import rsreu.microchad.service.services.EmployeeService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Api
@RequestMapping("/api/v1/employee")
@RestController
public class EmployeeController {

    private EmployeeService employeeService;
    private EmployeeRoleService employeeRoleService;

    public EmployeeController(@Autowired EmployeeService employeeService,
                              @Autowired EmployeeRoleService employeeRoleService) {
        this.employeeService = employeeService;
        this.employeeRoleService = employeeRoleService;
    }

    @PostMapping(value = "/")
    @ApiOperation(value = "Добавление работника")
    public ResponseEntity<Boolean> add(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.save(employeeDto), HttpStatus.CREATED);
    }


    @GetMapping(value = "/id={id}")
    @ApiOperation(value = "Получение информации о работнике")
    public ResponseEntity get(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
        }
        catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/")
    @ApiOperation(value = "Получение информации о всех работниках")
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/id={id}")
    @ApiOperation(value = "Удаление работника")
    public ResponseEntity delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/id={id}")
    @ApiOperation(value = "Обновление работника")
    public ResponseEntity<Boolean> update(@RequestBody EmployeeDto employee) {
        try {
           return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
       }
       catch (NoSuchElementException exception) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @ApiOperation(value = "/id={id}/dep={dep}/role")
    @PostMapping(value = "Добавление роли работнику")
    public ResponseEntity addRole(@PathVariable Long id, @PathVariable Long dep, @RequestBody RoleDto role) {
        try {
            employeeRoleService.save(EmployeeRoleDto.builder()
                    .employee(id)
                    .role(role.getId())
                    .department(dep)
                    .build());
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("ok");
    }


}
