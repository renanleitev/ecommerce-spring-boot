package spring.ecommerce.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import spring.ecommerce.domain.Product;
import spring.ecommerce.model.ProductDTO;
import spring.ecommerce.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // Pagination products (pageNumber, pageSize)
    @GetMapping("/findProduct")
    public List<Product> getPaginatedProducts(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {
        return productService.filterProducts(pageNumber, pageSize);
    }

    // Find products (by name)
    @GetMapping("/findProductByName")
    public List<Product> getProductByName(@RequestParam String name) {
        return productService.findProductByName(name);
    }

    // Find products (by name LIKE)
    @GetMapping("/findProductByNameLike")
    public List<Product> getProductByNameLike(@RequestParam String name) {
        return productService.findProductByNameLike(name);
    }

    // Find products (by additionalFeature)
    @GetMapping("/findProductByAdditionalFeature")
    public List<Product> getProductByAdditionalFeature(@RequestParam String additionalFeature) {
        return productService.findProductByAdditionalFeatures(additionalFeature);
    }

    // Find products (by additionalFeature LIKE)
    @GetMapping("/findProductByAdditionalFeatureLike")
    public List<Product> getProductByAdditionalFeatureLike(@RequestParam String additionalFeature) {
        return productService.findProductByAdditionalFeaturesLike(additionalFeature);
    }

    // Find products (by os)
    @GetMapping("/findProductByOs")
    public List<Product> getProductByOs(@RequestParam String os) {
        return productService.findProductByOs(os);
    }

    // Find products (by os LIKE)
    @GetMapping("/findProductByOsLike")
    public List<Product> getProductByOsLike(@RequestParam String os) {
        return productService.findProductByOsLike(os);
    }

    // Find products (by price)
    @GetMapping("/findProductByPrice")
    public List<Product> getProductByPrice(@RequestParam String price) {
        return productService.findProductByPrice(price);
    }

    // GET products (by price GREATER THAN)
    @GetMapping("/findProductByPriceGreaterThan")
    public List<Product> getProductByPriceGreaterThan(@RequestParam String price) {
        return productService.findProductByPriceGreaterThan(price);
    }

    // GET products (by price GREATER THAN OR EQUAL TO)
    @GetMapping("/findProductByPriceGreaterThanOrEqualTo")
    public List<Product> getProductByPriceGreaterThanOrEqualTo(@RequestParam String price) {
        return productService.findProductByPriceGreaterThanOrEqualTo(price);
    }

    // GET products (by price LESS THAN)
    @GetMapping("/findProductByPriceLessThan")
    public List<Product> getProductByPriceLessThan(@RequestParam String price) {
        return productService.findProductByPriceLessThan(price);
    }

    // GET products (by price LESS THAN OR EQUAL TO)
    @GetMapping("/findProductByPriceLessThanOrEqualTo")
    public List<Product> getProductByPriceLessThanOrEqualTo(@RequestParam String price) {
        return productService.findProductByPriceLessThanOrEqualTo(price);
    }

    // GET products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    // GET products by id
    @GetMapping("/products/{id}")
    public Optional<Product> getProductById(@PathVariable(name = "id") final Long id) {
        return productService.findProductById(id);
    }

    // POST products
    @PostMapping("/products")
    public Long createProduct(@RequestBody @Valid final Product product) {
        final Long createdId = productService.createProductReturnId(product);
        return createdId;
    }

    // PUT products by id
    @PutMapping("/products/{id}")
    public void updateProduct(
            @PathVariable(name = "id") final Long id,
            @RequestBody @Valid final Product product) {
        productService.updateProductById(id, product);
    }

    // DELETE products by id
    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable(name = "id") final Long id) {
        productService.deleteProductById(id);
    }

}
