package spring.ecommerce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.ecommerce.model.Shopping;

import java.util.List;


public interface ShoppingRepository extends JpaRepository<Shopping, Long> {

    // TODO: Construir uma interface para abrigar a resposta da consulta SQL
/*    @Query(value = "USE ecommerce; SELECT users.name, products.name, shoppings.quantity, shoppings.total_price, shoppings.date_created FROM shoppings\n" +
            "LEFT JOIN users ON shoppings.user_id = users.id\n" +
            "LEFT JOIN products ON shoppings.product_id = products.id\n" +
            "WHERE users.id = 1", nativeQuery = true)
    List<Shopping> findAllByLeftJoin(String id);*/
}
