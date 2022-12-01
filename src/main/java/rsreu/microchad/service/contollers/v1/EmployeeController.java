package rsreu.microchad.service.contollers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsreu.microchad.service.entities.Employee;

@Api
@RequestMapping("/api/v1/employee")
@RestController
public class EmployeeController {

    @PostMapping(value = "/")
    @ApiOperation(value = "Добавление работника")
    public ResponseEntity add(@RequestBody Employee employee) {
        return ResponseEntity.ok("ok");
    }

    @PutMapping(value = "/id={id}")
    @ApiOperation(value = "Обновление работника")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Employee employee) {
        return ResponseEntity.ok("ok");
    }

    @GetMapping(value = "/id={id}")
    @ApiOperation(value = "Получение работника из базы")
    public ResponseEntity get(@PathVariable Long id) {
        return ResponseEntity.ok("ok");
    }
}
