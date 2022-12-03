package rsreu.microchad.service.contollers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsreu.microchad.service.dto.DepartmentDto;
import rsreu.microchad.service.dto.DepartmentProjectDto;
import rsreu.microchad.service.dto.ProjectDto;
import rsreu.microchad.service.services.DepartmentProjectService;
import rsreu.microchad.service.services.DepartmentsService;

import java.util.NoSuchElementException;

@Api
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    DepartmentsService departmentsService;
    DepartmentProjectService departmentProjectService;

    public DepartmentController(@Autowired DepartmentsService departmentsService,
                                @Autowired DepartmentProjectService departmentProjectService) {
        this.departmentsService = departmentsService;
        this.departmentProjectService = departmentProjectService;
    }

    @ApiOperation(value = "Получить информацию об отделе")
    @GetMapping(value = "/id={id}")
    public ResponseEntity get(Long id) {
        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "Обновить информацию об отделе")
    @PutMapping(value = "/id={id}")
    public ResponseEntity update(Long id, @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "Добавить информацию об отделе")
    @PostMapping
    public ResponseEntity add(@RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "Удалить информацию об отделе")
    @DeleteMapping(value = "/id={id}")
    public ResponseEntity remove(Long id) {
        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "Добавить прокт департаменту")
    @PostMapping(value = "/id={id}/project")
    public ResponseEntity addProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        try {
            departmentProjectService.save(DepartmentProjectDto.builder()
                    .project(id)
                    .department(projectDto.getId())
                    .build());
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("ok");
    }
}
