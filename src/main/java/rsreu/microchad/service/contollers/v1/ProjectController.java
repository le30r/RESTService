package rsreu.microchad.service.contollers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsreu.microchad.service.dto.ProjectDto;
import rsreu.microchad.service.services.ProjectService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Api
@RequestMapping("/api/v1/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    ProjectController(@Autowired ProjectService projectService) {
        this.projectService = projectService;
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
    public ResponseEntity remove(Long id) {
        if (projectService.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
