package spring.ecommerce.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.ecommerce.model.User;
import spring.ecommerce.service.UserService;

import java.util.List;
import java.util.Optional;

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

    // POST users
    @PostMapping
    public Long createUser(@RequestBody @Valid final User user) {
        final Long createdId = userService.createUserReturnId(user);
        return createdId;
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

    // LOGIN users
    @PostMapping("/login")
    public Boolean loginUser(@RequestBody @Valid final User user){
        return userService.loginUser(user);
    }
}
