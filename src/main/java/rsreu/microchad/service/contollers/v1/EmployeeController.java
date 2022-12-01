package rsreu.microchad.service.contollers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsreu.microchad.service.dto.EmployeeDto;
import rsreu.microchad.service.services.EmployeeService;

@Api
@RequestMapping("/api/v1/employee")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @PostMapping(value = "/")
    @ApiOperation(value = "Добавление работника")
    public ResponseEntity add(@RequestBody EmployeeDto employee) {
        return ResponseEntity.ok("ok");
    }

    @PutMapping(value = "/id={id}")
    @ApiOperation(value = "Обновление работника")
    public ResponseEntity update(@PathVariable Long id, @RequestBody EmployeeDto employee) {
        return ResponseEntity.ok("ok");
    }

    @GetMapping(value = "/id={id}")
    @ApiOperation(value = "Получение информации о работнике")
    public ResponseEntity get(@PathVariable Long id) {
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping(value = "/id={id}")
    @ApiOperation(value = "Удаление работника")
    public ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.ok("ok");
    }
}
