package spring.ecommerce.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.ecommerce.model.Product;
import spring.ecommerce.repos.ProductRepository;

import org.springframework.data.domain.Page;
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

    public Page<Product> findProduct (Map<String, String> customQuery, Pageable paging) {
        switch (customQuery.keySet().toString()) {
            case "[_name, _page, _limit]" -> {
                String name = customQuery.get("_name");
                return productRepository.findAllByNameLike(name, paging);
            }
            case "[_additionalFeatures, _page, _limit]" -> {
                String additionalFeatures = customQuery.get("_additionalFeatures");
                return productRepository.findAllByAdditionalFeaturesLike(additionalFeatures, paging);
            }
            case "[_description, _page, _limit]" -> {
                String description = customQuery.get("_description");
                return productRepository.findAllByDescriptionLike(description, paging);
            }
            case "[_os, _page, _limit]" -> {
                String os = customQuery.get("_os");
                return productRepository.findAllByOsLike(os, paging);
            }
            case "[_price, _page, _limit]" -> {
                String price = customQuery.get("_price");
                return productRepository.findAllByPrice(price, paging);
            }
            case "[_price, _operator, _page, _limit]" -> {
                String price = customQuery.get("_price");
                String operator = customQuery.get("_operator");
                Page<Product> productsList = switch (operator) {
                    case "EqualTo" -> productRepository.findAllByPrice(price, paging);
                    case "LessThan" -> productRepository.findAllByPriceLessThan(price, paging);
                    case "LessThanOrEqualTo" -> productRepository.findAllByPriceLessThanOrEqualTo(price, paging);
                    case "GreaterThan" -> productRepository.findAllByPriceGreaterThan(price, paging);
                    case "GreaterThanOrEqualTo" -> productRepository.findAllByPriceGreaterThanOrEqualTo(price, paging);
                    default -> productRepository.findAll(paging);
                };
                return productsList;
            }
            case "[_column, _order, _page, _limit]" -> {
                String column = customQuery.get("_column");
                String order = customQuery.get("_order");
                Page<Product> productsList = switch (order) {
                    case "ASC" -> productRepository.findAll(PageRequest.of(paging.getPageNumber(), paging.getPageSize(), Sort.by(Sort.Direction.ASC, column)));
                    case "DESC" -> productRepository.findAll(PageRequest.of(paging.getPageNumber(), paging.getPageSize(), Sort.by(Sort.Direction.DESC, column)));
                    default -> productRepository.findAll(paging);
                };
                return productsList;
            }
            default -> {
                return productRepository.findAll(paging);
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
