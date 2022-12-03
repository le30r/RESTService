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
import rsreu.microchad.service.dto.DepartmentDto;
import rsreu.microchad.service.dto.DepartmentProjectDto;
import rsreu.microchad.service.dto.ProjectDto;
import rsreu.microchad.service.entities.DepartmentProject;
import rsreu.microchad.service.services.DepartmentProjectService;
import rsreu.microchad.service.services.DepartmentsService;
import rsreu.microchad.service.services.ProjectService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Api
@RequestMapping("/api/v1/project")
public class ProjectController {

    private ProjectService projectService;
    private DepartmentProjectService departmentProjectService;

    ProjectController(@Autowired ProjectService projectService,
                      @Autowired DepartmentProjectService departmentProjectService) {
        this.projectService = projectService;
        this.departmentProjectService = departmentProjectService;
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
    @Operation(summary = "Получить информацию всех проектах",
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

    @ApiOperation(value = "Обновить информацию о проекте")
    @PutMapping(value = "/id={id}")
    public ResponseEntity<Boolean> update(@RequestBody ProjectDto projectDto) {
        try {
             return new ResponseEntity<>(projectService.update(projectDto), HttpStatus.OK);
        }
        catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
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
    @PostMapping
    public ResponseEntity add(@ApiParam("Информация о проекте") @RequestBody ProjectDto projectDto) {
        if (projectService.save(projectDto)) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "Удалить информацию о проекте")
    @DeleteMapping(value = "/id={id}")
    public ResponseEntity remove(@PathVariable Long id) {
        if (projectService.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Добавить для проекта ответственное подразделение")
    @PostMapping(value = "/id={id}/department")
    public ResponseEntity addDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto) {
        try {
            departmentProjectService.save(DepartmentProjectDto.builder()
                    .project(id)
                    .department(departmentDto.getId())
                    .build());
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("ok");
    }
}
