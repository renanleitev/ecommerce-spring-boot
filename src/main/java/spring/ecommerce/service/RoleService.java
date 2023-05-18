package spring.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.ecommerce.model.Role;
import spring.ecommerce.repos.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    public RoleService(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAllRoles () {
        return roleRepository.findAll();
    }
}
