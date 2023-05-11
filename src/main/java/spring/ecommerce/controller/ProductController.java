package spring.ecommerce.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import spring.ecommerce.domain.Product;
import spring.ecommerce.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    // Find products (by params)

    // 1ª Opção = Usando Map<String, String> e if/else
    // Se encontrar a key (param), retorna a função (param value)
/*    @GetMapping("/params")
    public List<Product> getProduct(@RequestParam Map<String, String> customQuery) {
        if (customQuery.containsKey("name")) {
            return productService.findProductByName(customQuery.get("name"));
        } else if (customQuery.containsKey("nameLike")) {
            return productService.findProductByNameLike(customQuery.get("nameLike"));
        } else if (customQuery.containsKey("additionalFeatures")) {
            return productService.findProductByAdditionalFeatures(customQuery.get("additionalFeatures"));
        } else if (customQuery.containsKey("additionalFeaturesLike")) {
            return productService.findProductByAdditionalFeaturesLike(customQuery.get("additionalFeaturesLike"));
        } else if (customQuery.containsKey("os")) {
            return productService.findProductByOs(customQuery.get("os"));
        } else if (customQuery.containsKey("osLike")) {
            return productService.findProductByOsLike(customQuery.get("osLike"));
        } else if (customQuery.containsKey("price")) {
            return productService.findProductByPrice(customQuery.get("price"));
        } else if (customQuery.containsKey("priceGreaterThan")) {
            return productService.findProductByPriceGreaterThan(customQuery.get("priceGreaterThan"));
        } else if (customQuery.containsKey("priceGreaterThanOrEqualTo")) {
            return productService.findProductByPriceGreaterThanOrEqualTo(customQuery.get("priceGreaterThanOrEqualTo"));
        } else if (customQuery.containsKey("priceLessThan")) {
            return productService.findProductByPriceLessThan(customQuery.get("priceLessThan"));
        } else if (customQuery.containsKey("priceLessThanOrEqualTo")) {
            return productService.findProductByPriceLessThanOrEqualTo(customQuery.get("priceLessThanOrEqualTo"));
        } else if (customQuery.containsKey("pageNumber") && customQuery.containsKey("pageSize")) {
            return productService.filterProducts(Integer.parseInt(customQuery.get("pageNumber")), Integer.parseInt(customQuery.get("pageSize")));
        } else {
            return productService.findAllProducts();
        }
    }*/

    // 2ª Opção = @RequestParam(required = false)
    // Se param for passado, retorna a função (param value)
    @GetMapping("/params")
    public List<Product> getProduct(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String nameLike,
            @RequestParam(required = false) String additionalFeatures,
            @RequestParam(required = false) String additionalFeaturesLike,
            @RequestParam(required = false) String os,
            @RequestParam(required = false) String osLike,
            @RequestParam(required = false) String price,
            @RequestParam(required = false) String priceGreaterThan,
            @RequestParam(required = false) String priceGreaterThanOrEqualTo,
            @RequestParam(required = false) String priceLessThan,
            @RequestParam(required = false) String priceLessThanOrEqualTo,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize
    ){
        if (name != null){
            return productService.findProductByName(name);
        } else if (nameLike != null) {
            return productService.findProductByNameLike(nameLike);
        } else if (additionalFeatures != null) {
            return productService.findProductByAdditionalFeatures(additionalFeatures);
        } else if (additionalFeaturesLike != null) {
            return productService.findProductByAdditionalFeaturesLike(additionalFeaturesLike);
        } else if (additionalFeatures != null) {
            return productService.findProductByAdditionalFeatures(additionalFeatures);
        } else if (additionalFeaturesLike != null) {
            return productService.findProductByAdditionalFeaturesLike(additionalFeaturesLike);
        } else if (os != null) {
            return productService.findProductByOs(os);
        } else if (osLike != null) {
            return productService.findProductByOsLike(osLike);
        }
        else if (price != null) {
            return productService.findProductByPrice(price);
        } else if (priceGreaterThan != null) {
            return productService.findProductByPriceGreaterThan(priceGreaterThan);
        } else if (priceGreaterThanOrEqualTo != null) {
            return productService.findProductByPriceGreaterThanOrEqualTo(priceGreaterThanOrEqualTo);
        } else if (priceLessThan != null) {
            return productService.findProductByPriceLessThan(priceLessThan);
        } else if (priceLessThan != null) {
            return productService.findProductByPriceLessThanOrEqualTo(priceLessThanOrEqualTo);
        } else if ((pageNumber != null) && (pageSize!= null)) {
            return productService.filterProducts(pageNumber, pageSize);
        } else {
            return productService.findAllProducts();
        }
    }

    // GET products by id
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable(name = "id") final Long id) {
        return productService.findProductById(id);
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
