package spring.ecommerce.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.ecommerce.model.Product;
import spring.ecommerce.model.Shopping;
import spring.ecommerce.model.ShoppingCart;
import spring.ecommerce.model.User;
import spring.ecommerce.repos.ShoppingRepository;

import java.util.List;

@Service
public class ShoppingService {
    @Autowired
    private final ShoppingRepository shoppingRepository;

    public ShoppingService(ShoppingRepository shoppingRepository) {
        this.shoppingRepository = shoppingRepository;
    }

    // POST products
    public void saveProductsToShopping(List<ShoppingCart> shoppingCart) {
        for (int i = 0; i < shoppingCart.size(); i++) {
            ShoppingCart item = shoppingCart.get(i);
            Product product = new Product();
            product.setId(Long.valueOf(item.getProductId()));
            User user = new User();
            user.setId(Long.valueOf(item.getUserId()));
            Shopping newItem = new Shopping();
            newItem.setProduct(product);
            newItem.setUser(user);
            newItem.setQuantity(item.getQuantity());
            newItem.setTotalPrice(item.getTotalPrice());
            shoppingRepository.save(newItem);
        }
    }

/*    public List<Shopping> showShoppings(String id){
        return shoppingRepository.findAllByLeftJoin(id);
    }*/
}
