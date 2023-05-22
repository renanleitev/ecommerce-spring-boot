package spring.ecommerce.controller;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.ecommerce.model.Shopping;
import spring.ecommerce.model.ShoppingCart;
import spring.ecommerce.model.ShoppingList;
import spring.ecommerce.service.ShoppingService;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = {
        "Authorization",
        "Origin",
        "Id"
})
@AllArgsConstructor
@RestController
@RequestMapping(value = "shoppings")
public class ShoppingController {

    private ShoppingService shoppingService;

    @PostMapping
    public ResponseEntity<Boolean> addProductsToShopping (@RequestBody List<ShoppingCart> shoppingCart){
        shoppingService.saveProductsToShopping(shoppingCart);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ShoppingList>> showProductsAndUsersInShoppings (@PathVariable(name = "id") String userId) {
        List<ShoppingList> list = shoppingService.showShoppings(userId);
        return ResponseEntity.ok().body(list);
    }
}
