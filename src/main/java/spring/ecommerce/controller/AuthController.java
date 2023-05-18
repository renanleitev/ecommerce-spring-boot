package spring.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.ecommerce.model.Role;
import spring.ecommerce.model.User;
import spring.ecommerce.service.AuthService;
import spring.ecommerce.service.RoleService;
import spring.ecommerce.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping(value = "auth")
public class AuthController {

    private AuthService authService;

    private UserService userService;

    private RoleService roleService;

    // Register User
    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody User user){
        Set<Role> roleSet = new HashSet<>();
        if (user.getEmail().equals("adminroot@email.com")) {
            roleSet.add(new Role("ADMIN"));
        } else {
            roleSet.add(new Role("USER"));
        }
        user.setRoles(roleSet);
        Long id = userService.createUserReturnId(user);
        if (id == null) {
            return ResponseEntity.ok().body(false);
        }
        String token = authService.login(user);
        return ResponseEntity.ok()
                .header("Bearer ", token)
                .header("Id ", id.toString())
                .body(true);
    }

    // Login User
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody User user){
        String token = authService.login(user);
        if (token == null) {
            return ResponseEntity.ok().body(false);
        }
        return ResponseEntity.ok()
                .header("Bearer ", token)
                .body(true);
    }

    // Edit User
    @PutMapping("/edit/{id}")
    public ResponseEntity<Boolean> updateUserById(@PathVariable(name = "id") final Long id,
                                                   @RequestBody @Valid final User user) {
        Boolean userExists = userService.updateUserById(id, user);
        if (!userExists) {
            return ResponseEntity.ok().body(false);
        }
        String token = authService.login(user);
        return ResponseEntity.ok()
                .header("Bearer ", token)
                .body(true);
    }

    // Delete User
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean>  deleteUserById(@PathVariable(name = "id") final Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().body(true);
    }

    // GET Roles
    @GetMapping("roles")
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok().body(roleService.findAllRoles());
    }
}
