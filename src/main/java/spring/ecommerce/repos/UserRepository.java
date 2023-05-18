package spring.ecommerce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.ecommerce.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

}
