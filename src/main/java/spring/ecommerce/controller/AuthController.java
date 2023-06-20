package spring.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.ecommerce.model.User;
import spring.ecommerce.service.AuthService;
import spring.ecommerce.service.UserService;
import java.util.Optional;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth", description = "API for auth model")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = {
        "Authorization",
        "Origin",
        "Id"
})
@AllArgsConstructor
@RestController
@RequestMapping(value = "auth")
public class AuthController {

    private AuthService authService;

    private UserService userService;

    // Register User
    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody User user){
        if (user.getEmail().equals("adminroot@email.com")) {
            user.setRole("ROLE_ADMIN");
        } else if (user.getEmail().equals("manager@email.com")) {
            user.setRole("ROLE_MANAGER");
        } else {
            user.setRole("ROLE_USER");
        }
        Long id = userService.createUserReturnId(user);
        if (id == null) {
            return ResponseEntity.ok().body(false);
        }
        String token = authService.login(user);
        return ResponseEntity.ok()
                .header("Authorization", token)
                .header("Id", id.toString())
                .body(true);
    }

    // Login User
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        String token = authService.login(user);
        Optional<User> userDB = userService.findUserByUsername(user.getUsername());
        if (userDB.isPresent()){
            User userLogin = userDB.get();
            userLogin.setPassword(user.getPassword());
            return ResponseEntity.ok()
                    .header("Authorization", token)
                    .body(userLogin);
        } else {
            return ResponseEntity.ok().body(user);
        }

    }

    // Edit User
    @PutMapping("/edit")
    public ResponseEntity<Boolean> updateUserById(@RequestBody @Valid final User user) {
        Boolean userExists = userService.updateUserById(user);
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
