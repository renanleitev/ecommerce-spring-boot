package spring.ecommerce.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.ecommerce.model.Product;
import spring.ecommerce.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);

    @Query("SELECT u FROM User u WHERE u.username LIKE CONCAT('%',:username,'%')")
    Page<User> findAllByUsernameLike(@Param("username") String username, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.name LIKE CONCAT('%',:name,'%')")
    Page<User> findAllByNameLike(@Param("name") String name, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.surname LIKE CONCAT('%',:surname,'%')")
    Page<User> findAllBySurnameLike(@Param("surname") String surname, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.address LIKE CONCAT('%',:address,'%')")
    Page<User> findAllByAddressLike(@Param("address") String address, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.email LIKE CONCAT('%',:email,'%')")
    Page<User> findAllByEmailLike(@Param("email") String email, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.role LIKE CONCAT('%',:role,'%')")
    Page<User> findAllByRoleLike(@Param("role") String role, Pageable pageable);
}
