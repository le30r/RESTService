package rsreu.microchad.service.contollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsreu.microchad.service.entities.Employee;


@RequestMapping("/employee")
@RestController
public class EmployeeController {

    @PostMapping(value = "/v1")
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
