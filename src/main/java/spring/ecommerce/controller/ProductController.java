package spring.ecommerce.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import spring.ecommerce.model.Product;
import spring.ecommerce.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = {
        "Authorization",
        "Origin",
        "x-total-pages",
        "x-total-count",
})
@RestController
@RequestMapping(value = "products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET products by id
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable(name = "id") final Long id) {
        return productService.findProductById(id);
    }

    // GET products by query
    @GetMapping
    public Map<String, List<Product>> getProductByQuery(@RequestParam Map<String, String> customQuery){
        Map<String, List<Product>> response = new HashMap<>();
        response.put("data", productService.findProduct(customQuery));
        return response;
    }

    // GET products by page

    @GetMapping("/pagination")
    public ResponseEntity<List<Product>> getProductByPage(@RequestParam String _page, @RequestParam String _limit){
        Integer pageNumber = Integer.parseInt(_page);
        Integer pageSize = Integer.parseInt(_limit);
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<Product> pagedResult = productService.findProductByPage(paging);
        Integer totalPages = pagedResult.getTotalPages();
        Long totalProducts = pagedResult.getTotalElements();
        return ResponseEntity.ok()
                .header("x-total-pages", totalPages.toString())
                .header("x-total-count", totalProducts.toString())
                .body(pagedResult.toList());
    }

    // POST products
    @PostMapping
    public Long createProduct(@RequestBody @Valid final Product product) {
        final Long createdId = productService.createProductReturnId(product);
        return createdId;
    }

    // PUT products by id
    @PutMapping("/{id}")
    public void updateProduct(
            @PathVariable(name = "id") final Long id,
            @RequestBody @Valid final Product product) {
        productService.updateProductById(id, product);
    }

    // DELETE products by id
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable(name = "id") final Long id) {
        productService.deleteProductById(id);
    }

}
