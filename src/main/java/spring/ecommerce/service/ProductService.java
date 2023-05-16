package spring.ecommerce.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spring.ecommerce.model.Product;
import spring.ecommerce.repos.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET products (by id)
    public Optional<Product> findProductById (final Long id) {
        return productRepository.findById(id);
    }

    public ResponseEntity<List<Product>> findProduct (Map<String, String> customQuery) {
        switch (customQuery.keySet().toString()){
            case "[_name]":
                String name = customQuery.get("_name");
                return ResponseEntity.ok().body(productRepository.findAllByNameLike(name));
            case "[_additionalFeatures]":
                String additionalFeatures = customQuery.get("_additionalFeatures");
                return ResponseEntity.ok().body(productRepository.findAllByAdditionalFeaturesLike(additionalFeatures));
            case "[_os]":
                String os = customQuery.get("_os");
                return ResponseEntity.ok().body(productRepository.findAllByOsLike(os));
            case "[_price]":
                return ResponseEntity.ok().body(productRepository.findAllByPrice(customQuery.get("_price")));
            case "[_price, _operator]":
                String price = customQuery.get("_price");
                String operator = customQuery.get("_operator");
                List<Product> productsList;
                switch (operator){
                    case "EqualTo":
                        productsList = productRepository.findAllByPrice(price);
                        break;
                    case "LessThan":
                        productsList = productRepository.findAllByPriceLessThan(price);
                        break;
                    case "LessThanOrEqualTo":
                        productsList = productRepository.findAllByPriceLessThanOrEqualTo(price);
                        break;
                    case "GreaterThan":
                        productsList = productRepository.findAllByPriceGreaterThan(price);
                        break;
                    case "GreaterThanOrEqualTo":
                        productsList = productRepository.findAllByPriceGreaterThanOrEqualTo(price);
                        break;
                    default:
                        productsList = productRepository.findAll();
                }
                return ResponseEntity.ok().body(productsList);
            case "[_page, _limit]":
                Integer pageNumber = Integer.parseInt(customQuery.get("_page"));
                Integer pageSize = Integer.parseInt(customQuery.get("_limit"));
                Pageable paging = PageRequest.of(pageNumber, pageSize);
                Page<Product> pagedResult = productRepository.findAll(paging);
                Integer totalPages = pagedResult.getTotalPages();
                Long totalProducts = pagedResult.getTotalElements();
                return ResponseEntity.ok()
                        .header("x-total-pages", totalPages.toString())
                        .header("x-total-count", totalProducts.toString())
                        .body(pagedResult.toList());
            default:
                return ResponseEntity.ok().body(productRepository.findAll());
        }
    }

    // POST products
    public Long createProductReturnId(final Product product) {
        return productRepository.save(product).getId();
    }

    // PUT products
    public void updateProductById(final Long id, final Product productUpdated) {
        productRepository.findById(id)
                .map(product -> {
                    product.setName(productUpdated.getName());
                    product.setOs(productUpdated.getOs());
                    product.setPrice(productUpdated.getPrice());
                    product.setDescription(productUpdated.getDescription());
                    product.setAdditionalFeatures(productUpdated.getAdditionalFeatures());
                    product.setImage(productUpdated.getImage());
                    productRepository.save(product);
                    return null;
                });
    }

    // DELETE products by id
    public void deleteProductById(final Long id) {
        productRepository.deleteById(id);
    }

}
