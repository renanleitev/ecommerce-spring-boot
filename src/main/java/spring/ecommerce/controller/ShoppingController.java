package spring.ecommerce.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.ecommerce.model.Shopping;
import spring.ecommerce.service.ShoppingService;
import spring.ecommerce.service.ShoppingToList;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "shoppings")
public class ShoppingController {

    private ShoppingService shoppingService;

    @PostMapping
    public ResponseEntity<Boolean> addProductsToShopping (@RequestBody ShoppingToList shoppingList){
        shoppingService.saveProductsToShopping(shoppingList.getShopping());
        return ResponseEntity.ok().body(true);
    }
}
