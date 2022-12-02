package rsreu.microchad.service.contollers.v1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import rsreu.microchad.service.ServiceApplication;
import rsreu.microchad.service.dto.EmployeeDto;
import rsreu.microchad.service.entities.Employee;
import rsreu.microchad.service.repositories.EmployeeRepository;
import rsreu.microchad.service.services.EmployeeRoleService;
import rsreu.microchad.service.services.EmployeeService;

import java.sql.Date;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=EmployeeController.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository repository;

    @MockBean
    private EmployeeService service;

    @MockBean
    private EmployeeRoleService employeeRoleService;

    @Test
    public void add() {

    }

    @Test
    public void getAll() throws Exception {
        Long id = 3L;
        //when(repository.findById(id)).thenReturn(Optional.of(Employee.builder().name("Ivan").build()));
        when(service.findById(id)).thenReturn(EmployeeDto.builder().name("Ivan").build());
        mockMvc.perform(get("/api/v1/employee/id={id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void addRole() {
    }
}