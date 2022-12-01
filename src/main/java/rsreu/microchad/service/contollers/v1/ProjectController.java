package rsreu.microchad.service.contollers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsreu.microchad.service.dto.DepartmentDto;
import rsreu.microchad.service.dto.ProjectDto;
import rsreu.microchad.service.services.ProjectService;

@RestController
@Api
@RequestMapping("/api/v1/project")
public class ProjectController {

    private ProjectService projectService;

    ProjectController(@Autowired ProjectService projectService) {
        this.projectService = projectService;
    }

    @ApiOperation(value = "Получить информацию о проекте")
    @GetMapping(value = "/id={id}")
    public ResponseEntity get(Long id) {
        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "Обновить информацию о проекте")
    @PutMapping(value = "/id={id}")
    public ResponseEntity update(Long id, @RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "Добавить информацию о проекте")
    @PostMapping
    public ResponseEntity add(@RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "Удалить информацию о проекте")
    @DeleteMapping(value = "/id={id}")
    public ResponseEntity remove(Long id) {
        return ResponseEntity.ok("ok");
    }



}
