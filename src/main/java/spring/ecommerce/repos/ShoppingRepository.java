package spring.ecommerce.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.ecommerce.model.Shopping;
import spring.ecommerce.model.ShoppingList;

import java.util.List;


public interface ShoppingRepository extends JpaRepository<Shopping, Long> {

    // Consultas JPQL são diferentes do SQL normal
    // O foco é nas entidades, não no nome das tabelas
    // https://stackoverflow.com/questions/36328063/how-to-return-a-custom-object-from-a-spring-data-jpa-group-by-query/36329166#36329166
    @Query("SELECT new spring.ecommerce.model.ShoppingList(u.name, p.name, s.quantity, s.totalPrice, s.dateCreated) FROM Shopping s LEFT JOIN s.user u LEFT JOIN s.product p WHERE u.id = :id")
    Page<ShoppingList> findAllShoppingsByUserId(@Param("id") String id, Pageable pageable);
}
