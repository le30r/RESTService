package rsreu.microchad.service.controllers.v1.tests;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.client.MockRestServiceServer;
import rsreu.microchad.service.contollers.v1.EmployeeController;
import rsreu.microchad.service.repositories.EmployeeRepository;
import rsreu.microchad.service.services.EmployeeService;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest(classes = {EmployeeRepository.class, EmployeeService.class})
@RestClientTest(EmployeeController.class)
public class EmployeeControllerTests
{
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private EmployeeService service;

    @Autowired
    private MockRestServiceServer server;


}
