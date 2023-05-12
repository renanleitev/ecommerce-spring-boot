package spring.ecommerce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.ecommerce.model.Shopping;


public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
}
