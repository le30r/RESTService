package rsreu.microchad.service.contollers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liquibase.pro.packaged.A;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsreu.microchad.service.entities.Employee;

@Api
@RequestMapping("/employee")
@RestController
public class EmployeeController {

    @PostMapping(value = "/v1")
    @ApiOperation(value = "Добавление работника")
    public ResponseEntity add(@RequestBody Employee employee) {
        return ResponseEntity.ok("ok");
    }

    @PutMapping(value = "/v1/{id}")
    @ApiOperation(value = "Обновление работника")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Employee employee) {
        return ResponseEntity.ok("ok");
    }

    @GetMapping(value = "/v1/id={id}")
    @ApiOperation(value = "Получение работника из базы")
    public ResponseEntity get(@PathVariable Long id) {
        return ResponseEntity.ok("ok");
    }
}
