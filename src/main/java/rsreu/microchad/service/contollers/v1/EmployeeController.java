package rsreu.microchad.service.contollers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import liquibase.repackaged.org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsreu.microchad.service.dto.EmployeeDto;
import rsreu.microchad.service.dto.EmployeeRoleDto;
import rsreu.microchad.service.dto.ProjectDto;
import rsreu.microchad.service.dto.RoleDto;
import rsreu.microchad.service.repositories.EmployeeRoleRepository;
import rsreu.microchad.service.services.EmployeeRoleService;
import rsreu.microchad.service.services.EmployeeService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Api(tags = "Управление сотрудниками")
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
    @ApiOperation(value = "Добавление сотрудника")
    public ResponseEntity<Boolean> add(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.save(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/id={id}")
    @Operation(summary = "Получить информацию о сотруднике",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация о сотруднике",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeDto.class))),
                    @ApiResponse(responseCode = "404", description = "Сотрудник с таким id не найден"),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            })
    public ResponseEntity get(@ApiParam("ID сотрудника") @PathVariable Long id) {
        try {
            return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
        }
        catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/")
    @Operation(summary = "Получить информацию о всех сотрудниках",
            description = "Возвращает список всех сотрудников",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Список всех сотрудников",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EmployeeDto.class)))),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен")
            })
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Удалить сотрудника",
            description = "Удаление информации о сотруднике по его id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Удаление произошло успешно",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class)))),
                    @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
                    @ApiResponse(responseCode = "404", description = "Сотрудник с таким ID не найден"),
            })
    @DeleteMapping(value = "/id={id}")
    public ResponseEntity delete(@ApiParam("ID сотрудника") @PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновить информацию о сотруднике",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация успешно обновлена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Boolean.class))),
                    @ApiResponse(responseCode = "404", description = "Сотрудник с такими параметрами не найден"),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            })
    @PutMapping(value = "/")
    public ResponseEntity<Boolean> update(@ApiParam("Информация о сотруднике") @RequestBody EmployeeDto employee) {
        try {
           return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
       }
       catch (NoSuchElementException exception) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @Operation(summary = "Добавить для сотрудника роль",
            description = "Добавление роли для сотрудника",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Добавление произошло успешно",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class)))),
                    @ApiResponse(responseCode = "201", description = "Добавлено успешно"),
                    @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
                    @ApiResponse(responseCode = "404", description = "Сотрудник с таким ID не найден"),
            })
    @PostMapping(value = "/id={id}/dep={dep}/role")
    public ResponseEntity addRole(@ApiParam("ID сотрудника") @PathVariable Long id,
                                  @ApiParam("ID подразделения") @PathVariable Long dep,
                                  @ApiParam("Информация о роли") @RequestBody RoleDto role) {
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
