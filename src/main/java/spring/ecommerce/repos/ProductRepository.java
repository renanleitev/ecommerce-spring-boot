package spring.ecommerce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.ecommerce.domain.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
