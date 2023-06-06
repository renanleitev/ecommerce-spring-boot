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
    // Procurando por nome exato
    // Ex: Marca = Apple, Xiaomi, etc.
    List<Product> findAllByName(String name);

    // LIKE = Mais lento (não procura por índice)
    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name,'%')")
    Page<Product> findAllByNameLike(@Param("name") String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.os LIKE CONCAT('%',:os,'%')")
    Page<Product> findAllByOsLike(@Param("os") String os, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.additionalFeatures LIKE CONCAT('%',:additionalFeatures,'%')")
    Page<Product> findAllByAdditionalFeaturesLike(@Param("additionalFeatures") String additionalFeatures, Pageable pageable);

    // Procurando por preço exato
    Page<Product> findAllByPrice(String price, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.price < :price")
    Page<Product> findAllByPriceLessThan(@Param("price") String price, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.price <= :price")
    Page<Product> findAllByPriceLessThanOrEqualTo(@Param("price") String price, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    Page<Product> findAllByPriceGreaterThan(@Param("price") String price, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.price >= :price")
    Page<Product> findAllByPriceGreaterThanOrEqualTo(@Param("price") String price, Pageable pageable);

}
