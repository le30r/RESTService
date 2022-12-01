package rsreu.microchad.service.contollers.v1;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsreu.microchad.service.dto.DepartmentDto;
import rsreu.microchad.service.entities.Department;
import rsreu.microchad.service.services.DepartmentsService;

@Api
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    @Autowired
    DepartmentsService departmentsService;


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
}
