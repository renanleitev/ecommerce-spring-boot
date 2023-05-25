package spring.ecommerce.controller;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.ecommerce.model.Product;
import spring.ecommerce.model.User;
import spring.ecommerce.service.UserService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = {
        "Authorization",
        "Origin",
        "Id",
        "x-total-pages",
        "x-total-count",
})
@AllArgsConstructor
@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    private UserService userService;

    // GET users with pagination (pageNumber, pageSize)
    @GetMapping("/pagination")
    public ResponseEntity<List<User>> getPaginatedUsers(
            @RequestParam(required = false) Integer _page,
            @RequestParam(required = false) Integer _limit) {
        if ((_page != null) && (_limit != null)) {
            Integer pageNumber = _page;
            Integer pageSize = _limit;
            Pageable paging = PageRequest.of(pageNumber, pageSize);
            Page<User> pagedResult = userService.findUserByPage(paging);
            Integer totalPages = pagedResult.getTotalPages();
            Long totalUsers = pagedResult.getTotalElements();
            return ResponseEntity.ok()
                    .header("x-total-pages", totalPages.toString())
                    .header("x-total-count", totalUsers.toString())
                    .body(pagedResult.toList());
        } else {
            return ResponseEntity.ok().body(userService.findAllUsers());
        }
    }

    // GET users by id
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable(name = "id") final Long id) {
        return userService.findUserById(id);
    }

}
