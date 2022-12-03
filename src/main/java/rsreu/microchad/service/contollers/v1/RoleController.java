package rsreu.microchad.service.contollers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsreu.microchad.service.dto.ProjectDto;
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

    @Operation(summary = "Получить информацию о роли",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация о роли",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RoleDto.class))),
                    @ApiResponse(responseCode = "404", description = "Роль с таким id не найдена"),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            })
    @GetMapping(value = "/id={id}")
    public ResponseEntity get(@ApiParam("ID роли") @PathVariable Long id) {
        try {
            return new ResponseEntity<>(roleService.findById(id), HttpStatus.ACCEPTED);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Обновить информацию о роли",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация успешно обновлена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Boolean.class))),
                    @ApiResponse(responseCode = "404", description = "Оль с такими параметрами не найден"),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            })
    @PutMapping(value = "/")
    public ResponseEntity update(@ApiParam("Информация о роли") @RequestBody RoleDto roleDto) {
        try {
            return new ResponseEntity<>(roleService.update(roleDto), HttpStatus.OK);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Добавить роль",
            description = "Добавление роли в систему по описанию",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Роль добавлена успешно",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class)))),
                    @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
                    @ApiResponse(responseCode = "409", description = "Проект с таким ID уже существует")
            })
    @PostMapping("/")
    public ResponseEntity add(@ApiParam("Информация о роли") @RequestBody RoleDto roleDto) {
        if (roleService.save(roleDto)) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @Operation(summary = "Удалить роль",
            description = "Удаление информации о роли по ее id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Удаление произошло успешно",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class)))),
                    @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
                    @ApiResponse(responseCode = "404", description = "Роль с таким ID не найден"),
            })
    @DeleteMapping(value = "/id={id}")
    public ResponseEntity remove(@ApiParam("ID роли") @PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/")
    @Operation(summary = "Получить информацию о всех ролях",
            description = "Возвращает список всех ролей",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Список всех ролей",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = RoleDto.class)))),
                    @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен")
            })
    public ResponseEntity<List<RoleDto>> getAll() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }
}
