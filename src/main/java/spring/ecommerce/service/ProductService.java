package spring.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.ecommerce.domain.Product;
import spring.ecommerce.model.ProductDTO;
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

    public List<ProductDTO> findAll() {
        final List<Product> products = productRepository.findAll(Sort.by("id"));
        return products.stream()
                .map((product) -> mapToDTO(product, new ProductDTO()))
                .toList();
    }

    public ProductDTO get(final Long id) {
        return productRepository.findById(id)
                .map(product -> mapToDTO(product, new ProductDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getId();
    }

    public void update(final Long id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setAdditionalFeatures(product.getAdditionalFeatures());
        productDTO.setOs(product.getOs());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage());
        return productDTO;
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setName(productDTO.getName());
        product.setAdditionalFeatures(productDTO.getAdditionalFeatures());
        product.setOs(productDTO.getOs());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        return product;
    }

    public List<Product> filterProducts (Integer pageNumber, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<Product> pagedResult = productRepository.findAll(paging);
        return pagedResult.toList();
    }

}
