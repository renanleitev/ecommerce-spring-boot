package spring.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.ecommerce.model.Shopping;
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
    public void saveProductsToShopping(List<Shopping> shoppingList) {
        // TODO: Implementar loop para array de objetos
            shoppingRepository.save(shoppingList.get(0));
    }
}
