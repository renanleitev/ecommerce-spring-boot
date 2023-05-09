package spring.ecommerce.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.ecommerce.domain.Product;
import spring.ecommerce.domain.Shopping;
import spring.ecommerce.domain.User;
import spring.ecommerce.model.ShoppingDTO;
import spring.ecommerce.repos.ProductRepository;
import spring.ecommerce.repos.ShoppingRepository;
import spring.ecommerce.repos.UserRepository;
import spring.ecommerce.util.NotFoundException;


@Service
public class ShoppingService {

    private final ShoppingRepository shoppingRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShoppingService(final ShoppingRepository shoppingRepository,
            final UserRepository userRepository, final ProductRepository productRepository) {
        this.shoppingRepository = shoppingRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<ShoppingDTO> findAll() {
        final List<Shopping> shoppings = shoppingRepository.findAll(Sort.by("id"));
        return shoppings.stream()
                .map((shopping) -> mapToDTO(shopping, new ShoppingDTO()))
                .toList();
    }

    public ShoppingDTO get(final Long id) {
        return shoppingRepository.findById(id)
                .map(shopping -> mapToDTO(shopping, new ShoppingDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ShoppingDTO shoppingDTO) {
        final Shopping shopping = new Shopping();
        mapToEntity(shoppingDTO, shopping);
        return shoppingRepository.save(shopping).getId();
    }

    public void update(final Long id, final ShoppingDTO shoppingDTO) {
        final Shopping shopping = shoppingRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(shoppingDTO, shopping);
        shoppingRepository.save(shopping);
    }

    public void delete(final Long id) {
        shoppingRepository.deleteById(id);
    }

    private ShoppingDTO mapToDTO(final Shopping shopping, final ShoppingDTO shoppingDTO) {
        shoppingDTO.setId(shopping.getId());
        shoppingDTO.setQuantity(shopping.getQuantity());
        shoppingDTO.setTotalPrice(shopping.getTotalPrice());
        shoppingDTO.setUser(shopping.getUser() == null ? null : shopping.getUser().getId());
        shoppingDTO.setProduct(shopping.getProduct() == null ? null : shopping.getProduct().getId());
        return shoppingDTO;
    }

    private Shopping mapToEntity(final ShoppingDTO shoppingDTO, final Shopping shopping) {
        shopping.setQuantity(shoppingDTO.getQuantity());
        shopping.setTotalPrice(shoppingDTO.getTotalPrice());
        final User user = shoppingDTO.getUser() == null ? null : userRepository.findById(shoppingDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        shopping.setUser(user);
        final Product product = shoppingDTO.getProduct() == null ? null : productRepository.findById(shoppingDTO.getProduct())
                .orElseThrow(() -> new NotFoundException("product not found"));
        shopping.setProduct(product);
        return shopping;
    }

}
