package spring.ecommerce.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.ecommerce.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Pode colocar pageable como segundo argumento, caso queira filtrar por página

    // EQUAL = Mais rápido (procura por índice)
    // Ex: Marca = Apple, Xiaomi, etc.
    List<Product> findAllByName(String name);

    // LIKE = Mais lento (não procura por índice)
    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name,'%')")
    List<Product> findAllByNameLike(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.os LIKE CONCAT('%',:os,'%')")
    List<Product> findAllByOsLike(@Param("os") String os);

    @Query("SELECT p FROM Product p WHERE p.additionalFeatures LIKE CONCAT('%',:additionalFeatures,'%')")
    List<Product> findAllByAdditionalFeaturesLike(@Param("additionalFeatures") String additionalFeatures);

    // products?price=200.67&operator=GreaterThan
    // Evitar usar lógica no SQL
    // TODO: Pesquisar sobre uso do OR ou LIKE em outros bancos de dados
    // Escrever por "extenso" o nome das colunas para filtrar no SQL
    // Evitar usar astericos ("*" / "p") no SQL
    @Query("SELECT p FROM Product p WHERE " +
            "(:operator = 'EqualTo' and p.price = :price) OR" +
            "(:operator = 'LessThan' and p.price < :price) OR" +
            "(:operator = 'LessThanOrEqualTo' and p.price <= :price) OR" +
            "(:operator = 'GreaterThan' and p.price > :price) OR" +
            "(:operator = 'GreaterThanOrEqualTo' and p.price >= :price)")
    List<Product> findAllByPriceOperator(@Param("price") String price, @Param("operator") String operator);

}
