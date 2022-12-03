package rsreu.microchad.service.contollers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsreu.microchad.service.dto.RoleDto;
import rsreu.microchad.service.services.RoleService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Api(tags = "Управление ролями")
@RequestMapping("/api/v1/role")
public class RoleController {

    RoleService roleService;

    public RoleController(@Autowired RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "Получить информацию о роли")
    @GetMapping(value = "/id={id}")
    public ResponseEntity get(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(roleService.findById(id), HttpStatus.ACCEPTED);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Обновить информацию о роли")
    @PutMapping(value = "/id={id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        try {
            return new ResponseEntity<>(roleService.update(roleDto), HttpStatus.OK);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Добавить роль")
    @PostMapping
    public ResponseEntity add(@RequestBody RoleDto roleDto) {
        if (roleService.save(roleDto)) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "Удалить информацию о роли")
    @DeleteMapping(value = "/id={id}")
    public ResponseEntity remove(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/")
    @ApiOperation(value = "Получение информации о всех ролях")
    public ResponseEntity<List<RoleDto>> getAll() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }
}
