package rsreu.microchad.service.contollers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsreu.microchad.service.entities.Employee;

@Api
@RequestMapping("/employee")
@RestController
public class EmployeeController {

    @PostMapping(value = "/v1")
    @ApiOperation(value = "Add Employee")
    public ResponseEntity add(@RequestBody Employee employee) {
        return ResponseEntity.ok("ok");
    }

    @PutMapping(value = "/v1/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Employee employee) {
        return ResponseEntity.ok("ok");
    }

    @GetMapping(value = "/v1/id={id}")
    public ResponseEntity get(@PathVariable Long id) {
        return ResponseEntity.ok("ok");
    }
}
