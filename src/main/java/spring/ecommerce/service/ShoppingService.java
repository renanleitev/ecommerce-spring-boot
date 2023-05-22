package spring.ecommerce.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.ecommerce.model.*;
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
        for (ShoppingCart item : shoppingCart) {
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

    public List<ShoppingList> showShoppings(String userId){
        return shoppingRepository.findAllShoppingsByUserId(userId);
    }
}
