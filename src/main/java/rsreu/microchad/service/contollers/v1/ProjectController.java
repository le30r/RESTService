package rsreu.microchad.service.contollers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Получить информацию о проекте")
    @GetMapping(value = "/id={id}")
    public ResponseEntity<ProjectDto> get(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(projectService.findById(id), HttpStatus.OK);
        }
        catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    @ApiOperation(value = "Получить информацию о всех проектах")
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

    @ApiOperation(value = "Добавить информацию о проекте")
    @PostMapping
    public ResponseEntity add(@RequestBody ProjectDto projectDto) {
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
