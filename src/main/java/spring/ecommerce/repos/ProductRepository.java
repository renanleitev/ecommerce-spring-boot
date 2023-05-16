package spring.ecommerce.repos;

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
    // Procurando por nome exato
    // Ex: Marca = Apple, Xiaomi, etc.
    List<Product> findAllByName(String name);

    // LIKE = Mais lento (não procura por índice)
    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name,'%')")
    List<Product> findAllByNameLike(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.os LIKE CONCAT('%',:os,'%')")
    List<Product> findAllByOsLike(@Param("os") String os);

    @Query("SELECT p FROM Product p WHERE p.additionalFeatures LIKE CONCAT('%',:additionalFeatures,'%')")
    List<Product> findAllByAdditionalFeaturesLike(@Param("additionalFeatures") String additionalFeatures);

    // Procurando por preço exato
    List<Product> findAllByPrice(String price);

    @Query("SELECT p FROM Product p WHERE p.price < :price")
    List<Product> findAllByPriceLessThan(@Param("price") String price);

    @Query("SELECT p FROM Product p WHERE p.price <= :price")
    List<Product> findAllByPriceLessThanOrEqualTo(@Param("price") String price);

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findAllByPriceGreaterThan(@Param("price") String price);

    @Query("SELECT p FROM Product p WHERE p.price >= :price")
    List<Product> findAllByPriceGreaterThanOrEqualTo(@Param("price") String price);

}
