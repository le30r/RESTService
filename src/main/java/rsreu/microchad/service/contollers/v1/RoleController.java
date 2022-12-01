package rsreu.microchad.service.contollers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsreu.microchad.service.dto.ProjectDto;
import rsreu.microchad.service.dto.RoleDto;

@RestController
@Api
@RequestMapping("/api/v1/role")
public class RoleController {

    @ApiOperation(value = "Получить информацию о роли")
    @GetMapping(value = "/id={id}")
    public ResponseEntity get(Long id) {
        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "Обновить информацию о роли")
    @PutMapping(value = "/id={id}")
    public ResponseEntity update(Long id, @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "Добавить информацию о роли")
    @PostMapping
    public ResponseEntity add(@RequestBody RoleDto roleDto) {
        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "Удалить информацию о роли")
    @DeleteMapping(value = "/id={id}")
    public ResponseEntity remove(Long id) {
        return ResponseEntity.ok("ok");
    }
}
