package rsreu.microchad.service.services;

import liquibase.repackaged.org.apache.commons.lang3.NotImplementedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rsreu.microchad.service.dto.DepartmentProjectDto;
import rsreu.microchad.service.dto.RoleDto;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    public boolean save(RoleDto dto) {
        throw new NotImplementedException();
    }

    public boolean delete(Long id) {
        throw new NotImplementedException();
    }

    public boolean update(RoleDto dto) {
        throw new NotImplementedException();
    }

    public RoleDto findById(Long id) {
        throw new NotImplementedException();
    }

    public List<RoleDto> findAll() {
        throw new NotImplementedException();
    }


}
