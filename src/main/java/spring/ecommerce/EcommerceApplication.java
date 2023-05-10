package spring.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.ecommerce.domain.Product;
import spring.ecommerce.repos.ProductRepository;


@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

    @Autowired
    ProductRepository database;

    public static void main(final String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    // Toda vez que rodar o servidor, irá inserir os dados no MySQL
    @Override
    public void run(String... args) throws Exception {
/*        Product primeiro = new Product();
        primeiro.setName("Telefone");
        primeiro.setAdditionalFeatures("Falar");
        primeiro.setOs("Android");
        primeiro.setPrice("125.64");
        primeiro.setImage("Qualquer");
        primeiro.setDescription("blabla");
        database.save(primeiro);*/
    }

}
