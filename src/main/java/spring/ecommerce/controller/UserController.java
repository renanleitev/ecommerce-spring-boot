package spring.ecommerce.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.ecommerce.domain.Product;
import spring.ecommerce.domain.User;
import spring.ecommerce.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // Pagination users (pageNumber, pageSize)
    @GetMapping("/user")
    public List<User> getPaginatedUsers(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {
        return userService.filterUsers(pageNumber, pageSize);
    }

    // GET users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    // GET users by id
    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable(name = "id") final Long id) {
        return userService.findUserById(id);
    }

    // POST users
    @PostMapping("/users")
    public Long createUser(@RequestBody @Valid final User user) {
        final Long createdId = userService.createUserReturnId(user);
        return createdId;
    }

    // PUT users by id
    @PutMapping("/users/{id}")
    public void updateUser(
            @PathVariable(name = "id") final Long id,
            @RequestBody @Valid final User user) {
        userService.updateUserById(id, user);
    }

    // DELETE users by id
    @DeleteMapping("/users/{id}")
    public void deleteProductById(@PathVariable(name = "id") final Long id) {
        userService.deleteUserById(id);
    }
}
