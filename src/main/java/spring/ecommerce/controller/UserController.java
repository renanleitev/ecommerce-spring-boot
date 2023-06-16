package spring.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.ecommerce.model.Product;
import spring.ecommerce.model.User;
import spring.ecommerce.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users", description = "API for users model")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = {
        "Authorization",
        "Origin",
        "Id",
        "x-total-pages",
        "x-total-count",
})
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

    // GET products by query
    @GetMapping
    public ResponseEntity<List<User>>  getUserByQuery(@RequestParam Map<String, String> customQuery){
        Map<String, Page<Product>> response = new HashMap<>();
        Integer pageNumber = 0;
        Integer pageSize = 1;
        String page = customQuery.get("_page");
        String limit = customQuery.get("_limit");
        if (page != null && limit != null) {
            pageNumber = Integer.parseInt(page);
            pageSize = Integer.parseInt(limit);
        }
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<User> pagedResult = userService.findUser(customQuery, paging);
        Integer totalPages = pagedResult.getTotalPages();
        Long totalProducts = pagedResult.getTotalElements();
        return ResponseEntity.ok()
                .header("x-total-pages", totalPages.toString())
                .header("x-total-count", totalProducts.toString())
                .body(pagedResult.toList());
    }

    // GET users by id
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable(name = "id") final Long id) {
        return userService.findUserById(id);
    }

}
