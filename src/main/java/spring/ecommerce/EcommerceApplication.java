package spring.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.ecommerce.domain.Product;
import spring.ecommerce.domain.User;
import spring.ecommerce.repos.ProductRepository;
import spring.ecommerce.repos.UserRepository;


@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

    @Autowired
    ProductRepository databaseProduct;

    @Autowired
    UserRepository databaseUser;

    public static void main(final String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    // Toda vez que rodar o servidor, irá inserir os dados no MySQL
    // Usar apenas na primeira vez, descomentar e depois comentar o código abaixo
    // TODO: Pesquisar como converter o database.JSON para banco de dados
    @Override
    public void run(String... args) throws Exception {
/*        // Users
        String[] nameUser = {
              "admin",
              "Fulano",
        };

        String[] surname = {
              "root",
              "Silva",
        };

        String[] address = {
              "System Street",
              "Rua das Conchas",
        };

        String[] email = {
                "admin@email.com",
                "fulanosilva@email.com",
        };

        String[] password = {
                "adminroot",
                "123456",
        };

        for(int i = 0; i < nameUser.length; i++) {
            User insertUser = new User();
            insertUser.setName(nameUser[i]);
            insertUser.setSurname(surname[i]);
            insertUser.setAddress(address[i]);
            insertUser.setEmail(email[i]);
            insertUser.setPassword(password[i]);
            databaseUser.save(insertUser);
        }


        // Products
        String[] nameProduct = {
                "Apple iBook",
                "Samsung Phone",
                "Xiaomi Phone",
                "Dell XPS 12",
                "Asus Zenbook",
                "Lenovo Book",
                "Notebook Acer",
                "Chromebook Acer",
                "Tablet S6 Lite",
                "Ipad Apple",
        };

        String[] adFeat = {
                "Front Facing 1.3MP Camera",
                "Screen Resolution 4K",
                "Facial Scan",
                "Smart Screen",
                "Flashlight",
                "Voice Reader",
                "Supports SD Cards",
                "Trackpad",
                "Bluetooth",
                "CD Drive",
        };

        String[] os = {
                "Macintosh OS X 10.7",
                "Samsung UI 12",
                "Xiaomi OS",
                "Windows 11",
                "Ubuntu Linux",
                "Windows 10",
                "Windows ARM",
                "Chrome OS",
                "Android 13",
                "Mac OS Catalina",
        };

        Double[] price = {
                200.67,
                150.67,
                179.22,
                224.35,
                198.12,
                121.37,
                207.14,
                94.56,
                150.67,
                259.19,
        };

        String[] description = {
                "Apple iBook is the best in class computer for your professional and personal work.",
                "Samsung Phone will help you in your most important calls.",
                "Xiaomi Phone is the most affordable phone in the world.",
                "Dell is the most advanced notebook.",
                "Asus Zenbook is the most zen notebook in the world.",
                "Lenovo Book is a good notebook.",
                "Acer. The notebook of the future!",
                "Chromebook. Simple, but fast.",
                "S6 Lite. Runs everything.",
                "Ipad Apple. Expensive, but flexible.",
        };

        String[] images = {
                "https://upload.wikimedia.org/wikipedia/commons/9/9a/MacBook_Pro.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWcgtx1e8qnC6u3tFopg3hCb1L81Tdr1l4tw&usqp=CAU",
                "https://www.trustedreviews.com/wp-content/uploads/sites/54/2021/12/Xiaomi-12-leaks.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/4/4a/Dell_XPS_12_%282012%29.png",
                "https://live.staticflickr.com/6152/6254343204_2496efec64_b.jpg","https://www.trustedreviews.com/wp-content/uploads/sites/54/2022/01/Lenovo-Yoga-9i_Gen_7_Front_Facing_Oatmeal.png",
                "https://www.trustedreviews.com/wp-content/uploads/sites/54/2022/01/Nitro-5-AN517-05-scaled.jpg",
                "https://live.staticflickr.com/8251/8553344925_744ddcd41c_b.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR12SYcOFuEpZSitroA6I8EEE-EzC5iaxwkRg&usqp=CAU",
                "https://www.trustedreviews.com/wp-content/uploads/sites/54/2021/09/New-ipad-9.png",
        };

        for(int i = 0; i < nameProduct.length; i++) {
            Product insertProduct = new Product();
            insertProduct.setName(nameProduct[i]);
            insertProduct.setAdditionalFeatures(adFeat[i]);
            insertProduct.setOs(os[i]);
            insertProduct.setPrice(price[i]);
            insertProduct.setImage(images[i]);
            insertProduct.setDescription(description[i]);
            databaseProduct.save(insertProduct);
        }*/
    }

}
