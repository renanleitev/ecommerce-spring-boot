package spring.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import spring.ecommerce.domain.Product;
import spring.ecommerce.service.ProductService;

import java.util.List;


@RestController
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

    @GetMapping("/products")
    public List<Product> getPaginatedProducts(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {
        return productService.filterProductsByName(pageNumber, pageSize);
    }

}
