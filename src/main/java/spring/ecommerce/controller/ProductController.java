package spring.ecommerce.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import spring.ecommerce.model.Product;
import spring.ecommerce.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    @GetMapping()
    public ResponseEntity<List<Product>> getProductByQuery(@RequestParam Map<String, String> customQuery){
        return productService.findProduct(customQuery);
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
