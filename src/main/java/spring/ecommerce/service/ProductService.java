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

    public List<Product> findProduct (Map<String, String> customQuery) {
        switch (customQuery.keySet().toString()) {
            case "[_name]" -> {
                String name = customQuery.get("_name");
                return productRepository.findAllByNameLike(name);
            }
            case "[_additionalFeatures]" -> {
                String additionalFeatures = customQuery.get("_additionalFeatures");
                return productRepository.findAllByAdditionalFeaturesLike(additionalFeatures);
            }
            case "[_os]" -> {
                String os = customQuery.get("_os");
                return productRepository.findAllByOsLike(os);
            }
            case "[_price]" -> {
                return productRepository.findAllByPrice(customQuery.get("_price"));
            }
            case "[_price, _operator]" -> {
                String price = customQuery.get("_price");
                String operator = customQuery.get("_operator");
                List<Product> productsList = switch (operator) {
                    case "EqualTo" -> productRepository.findAllByPrice(price);
                    case "LessThan" -> productRepository.findAllByPriceLessThan(price);
                    case "LessThanOrEqualTo" -> productRepository.findAllByPriceLessThanOrEqualTo(price);
                    case "GreaterThan" -> productRepository.findAllByPriceGreaterThan(price);
                    case "GreaterThanOrEqualTo" -> productRepository.findAllByPriceGreaterThanOrEqualTo(price);
                    default -> productRepository.findAll();
                };
                return productsList;
            }
            default -> {
                return productRepository.findAll();
            }
        }
    }

    // GET products by page
    public Page<Product> findProductByPage (Pageable paging) {
        return productRepository.findAll(paging);
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
