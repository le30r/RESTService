package rsreu.microchad.service.contollers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsreu.microchad.service.dto.*;
import rsreu.microchad.service.services.DepartmentProjectService;
import rsreu.microchad.service.services.ProjectEmployeeService;
import rsreu.microchad.service.services.ProjectService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Api(tags = "Управление проектами")
@RequestMapping("/api/v1/project")
public class ProjectController {

    private ProjectService projectService;
    private DepartmentProjectService departmentProjectService;
    private ProjectEmployeeService projectEmployeeService;


    ProjectController(@Autowired ProjectService projectService,
                      @Autowired DepartmentProjectService departmentProjectService,
                      @Autowired ProjectEmployeeService projectEmployeeService) {
        this.projectService = projectService;
        this.departmentProjectService = departmentProjectService;
        this.projectEmployeeService = projectEmployeeService;
    }


    @Operation(summary = "Получить информацию о проекте",
    responses = {
            @ApiResponse(responseCode = "200",
                        description = "Информация о проекте",
                        content = @Content(mediaType = "application/json",
                                 schema = @Schema(implementation = ProjectDto.class))),
            @ApiResponse(responseCode = "404", description = "Проект с таким id не найден"),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @GetMapping(value = "/id={id}")
    public ResponseEntity<ProjectDto> get(@ApiParam("ID проекта") @PathVariable Long id) {
        try {
            return new ResponseEntity<>(projectService.findById(id), HttpStatus.OK);
        }
        catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/")
    @Operation(summary = "Получить информацию о всех проектах",
            description = "Возвращает список всех проектов",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Список всех проектов",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProjectDto.class)))),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен")
            })
    public ResponseEntity<List<ProjectDto>> getAll() {
        return new ResponseEntity(projectService.findAll(), HttpStatus.OK);
    }


    @Operation(summary = "Обновить информацию о проекте",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация успешно обновлена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Boolean.class))),
                    @ApiResponse(responseCode = "404", description = "Проект с такими параметрами не найден"),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            })
    @PutMapping(value = "/")
    public ResponseEntity<Boolean> update(@ApiParam("Информация о проекте") @RequestBody ProjectDto projectDto) {
        try {
             return new ResponseEntity<>(projectService.update(projectDto), HttpStatus.OK);
        }
        catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Operation(summary = "Добавить проект",
            description = "Добавление проекта в систему по описанию",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Проект добавлен успешно",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class)))),
                    @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
                    @ApiResponse(responseCode = "409", description = "Проект с таким ID уже существует")
            })
    @PostMapping("/")
    public ResponseEntity add(@ApiParam("Информация о проекте") @RequestBody ProjectDto projectDto) {
        if (projectService.save(projectDto)) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @Operation(summary = "Удалить проект",
            description = "Удаление информации о системе по его id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Удаление произошло успешно",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class)))),
                    @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
                    @ApiResponse(responseCode = "404", description = "Проект с таким ID не найден"),
            })
    @DeleteMapping(value = "/id={id}")
    public ResponseEntity remove(@ApiParam("id проекта") @PathVariable Long id) {
        try {
            projectService.delete(id);
            return new ResponseEntity( HttpStatus.OK);
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Добавить для проекта ответственное подразделение",
            description = "Добавление ответственного подразделения для проекта",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Добавление произошло успешно",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class)))),
                    @ApiResponse(responseCode = "201", description = "Добавлено успешно"),
                    @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
                    @ApiResponse(responseCode = "404", description = "Проект с таким ID не найден"),
            })
    @PostMapping(value = "/id={id}/department")
    public ResponseEntity addDepartment(@ApiParam("ID проекта") @PathVariable Long id,
                                        @ApiParam("Информация о подразделении") @RequestBody DepartmentDto departmentDto) {
        try {
            departmentProjectService.save(DepartmentProjectDto.builder()
                    .project(id)
                    .department(departmentDto.getId())
                    .build());
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Получить информацию о сотрудниках на проекте",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Список сотрудников",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EmployeeDto.class)))),
                    @ApiResponse(responseCode = "404", description = "Проект с таким id не найден"),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            })
    @GetMapping(value = "/{id}/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployees(@ApiParam("ID проекта") @PathVariable Long id) {
        try {
            return new ResponseEntity<>(projectEmployeeService.findAllEmployees(id), HttpStatus.OK);
        }
        catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Добавить сотрудника на проект",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Добавление произошло успешно",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class)))),
                    @ApiResponse(responseCode = "201", description = "Добавлено успешно"),
                    @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
                    @ApiResponse(responseCode = "404", description = "Проект или работник с таким ID не найден"),
            })
    @PostMapping(value = "/id={id}&employee={employee}")
    public ResponseEntity addEmployee(@ApiParam("ID проекта") @PathVariable Long id,
                                        @ApiParam("Номер сотрудника") @PathVariable Long employee) {
        try {
            projectEmployeeService.save(ProjectEmployeeDto.builder()
                            .employee(employee)
                            .project(id)
                    .build());
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
