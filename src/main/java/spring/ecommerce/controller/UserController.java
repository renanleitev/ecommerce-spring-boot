package spring.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.ecommerce.model.User;
import spring.ecommerce.service.UserService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = {
        "Authorization",
        "Origin",
        "Id"
})
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

}
