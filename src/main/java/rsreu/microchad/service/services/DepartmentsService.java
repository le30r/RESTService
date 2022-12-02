package rsreu.microchad.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rsreu.microchad.service.repositories.DepartmentRepository;

@Service
public class DepartmentsService {

    @Autowired
    private DepartmentRepository repository;


}
