package spring.ecommerce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.ecommerce.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {
}
