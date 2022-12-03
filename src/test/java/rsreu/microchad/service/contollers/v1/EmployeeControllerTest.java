package rsreu.microchad.service.contollers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import rsreu.microchad.service.dto.EmployeeDto;
import rsreu.microchad.service.repositories.EmployeeRepository;
import rsreu.microchad.service.services.EmployeeRoleService;
import rsreu.microchad.service.services.EmployeeService;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EmployeeController.class)
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

    @MockBean
    private RoleController roleController;

    @Test
    public void add() throws Exception {
        Long id = 3L;
        EmployeeDto dto = EmployeeDto.builder().id(id).build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(dto);
        when(service.save(dto)).thenReturn(true);
        mockMvc.perform(post("/api/v1/employee/", dto)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andDo(print());
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
    public void delete() throws Exception {
        Long id = 3L;
        when(service.delete(id)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/employee/id={id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testUpdateBadRequest() throws Exception {
        when(service.update(EmployeeDto.builder().name("Oleg").build())).thenThrow(new NoSuchElementException());
        mockMvc.perform(put("/api/v1/employee/"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    //TODO YOU NEED TO FIX UPDATE METHOD
    public void testUpdateNotFound() throws Exception {
        Long id = 3L;
        EmployeeDto dto = EmployeeDto.builder().id(id).build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(dto);
        when(service.update(dto)).thenThrow(new NoSuchElementException());
        mockMvc.perform(put("/api/v1/employee/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testUpdateMethodNotAllowed() throws Exception {
        Long id = 3L;
        mockMvc.perform(put("/api/v1/employee/id={id}", id))
                .andExpect(status().isMethodNotAllowed())
                .andDo(print());
    }

    @Test
    public void addRole() {

    }
}