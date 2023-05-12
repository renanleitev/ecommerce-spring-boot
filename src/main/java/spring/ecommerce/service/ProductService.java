package spring.ecommerce.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.ecommerce.model.Product;
import spring.ecommerce.repos.ProductRepository;
import spring.ecommerce.util.NotFoundException;

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

    // Pagination products (pageNumber, pageSize)
    public List<Product> filterProducts (Integer pageNumber, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<Product> pagedResult = productRepository.findAll(paging);
        return pagedResult.toList();
    }

    // GET products
    public List<Product> findAllProducts () {
        List<Product> productsList = productRepository.findAll();
        return productsList;
    }

    // GET products (by id)
    public Optional<Product> findProductById (final Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findProduct (Map<String, String> customQuery) {
        if (customQuery.containsKey("name")) {
            return productRepository.findAllByNameLike(customQuery.get("name"));
        } else if (customQuery.containsKey("additionalFeatures")) {
            return productRepository.findAllByAdditionalFeaturesLike(customQuery.get("additionalFeatures"));
        } else if (customQuery.containsKey("os")) {
            return productRepository.findAllByOsLike(customQuery.get("os"));
        } else if (customQuery.containsKey("price") && customQuery.containsKey("operator")) {
            return productRepository.findAllByPriceOperator(customQuery.get("price"), customQuery.get("operator"));
        } else if (customQuery.containsKey("pageNumber") && customQuery.containsKey("pageSize")) {
            Integer pageNumber = Integer.parseInt(customQuery.get("pageNumber"));
            Integer pageSize = Integer.parseInt(customQuery.get("pageSize"));
            Pageable paging = PageRequest.of(pageNumber, pageSize);
            Page<Product> pagedResult = productRepository.findAll(paging);
            return pagedResult.toList();
        } else {
            return productRepository.findAll();
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
