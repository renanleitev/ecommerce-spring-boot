package spring.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.ecommerce.model.User;
import spring.ecommerce.service.AuthService;
import spring.ecommerce.service.UserService;

@AllArgsConstructor
@RestController
@RequestMapping(value = "auth")
public class AuthController {

    private AuthService authService;

    private UserService userService;

    // Register User
    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody User user){
        Long id = userService.createUserReturnId(user);
        if (id == null) {
            return ResponseEntity.ok().body(false);
        }
        String token = authService.login(user);
        return ResponseEntity.ok()
                .header("Bearer ", token)
                .header("Id", id.toString())
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
}
