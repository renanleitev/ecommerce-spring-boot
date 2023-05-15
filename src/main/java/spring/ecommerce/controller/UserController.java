package spring.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.ecommerce.model.User;
import spring.ecommerce.service.AuthService;
import spring.ecommerce.service.UserService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    private UserService userService;

    // GET users with (optional) pagination (pageNumber, pageSize)
    @GetMapping
    public List<User> getPaginatedUsers(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {
        if ((pageNumber != null) && (pageSize != null)) {
            return userService.filterUsers(pageNumber, pageSize);
        } else {
            return userService.findAllUsers();
        }
    }

    // GET users by id
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable(name = "id") final Long id) {
        return userService.findUserById(id);
    }

    // PUT users by id
    @PutMapping("/{id}")
    public void updateUser(
            @PathVariable(name = "id") final Long id,
            @RequestBody @Valid final User user) {
        userService.updateUserById(id, user);
    }

    // DELETE users by id
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable(name = "id") final Long id) {
        userService.deleteUserById(id);
    }

    private AuthService authService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody User user){
        String token = authService.login(user);
        return ResponseEntity.ok(token);
    }
}
