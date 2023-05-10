package spring.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import spring.ecommerce.domain.Product;
import spring.ecommerce.domain.User;
import spring.ecommerce.service.ProductService;
import spring.ecommerce.service.UserService;

import java.util.List;


@RestController
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

    // Pagination Products (pageNumber, pageSize)
    @GetMapping("/products")
    public List<Product> getPaginatedProducts(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {
        return productService.filterProducts(pageNumber, pageSize);
    }

    // Pagination Users (pageNumber, pageSize)
    @GetMapping("/users")
    public List<User> getPaginatedUsers(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {
        return userService.filterUsers(pageNumber, pageSize);
    }

}
