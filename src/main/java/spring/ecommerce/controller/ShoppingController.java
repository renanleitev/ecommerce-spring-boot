package spring.ecommerce.controller;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        "x-total-pages",
        "x-total-count",
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
    public ResponseEntity<List<ShoppingList>> showProductsAndUsersInShoppings (
            @PathVariable(name = "id") String userId,
            @RequestParam Integer _page,
            @RequestParam Integer _limit) {
        Integer pageNumber = _page;
        Integer pageSize = _limit;
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<ShoppingList> shoppingListResult = shoppingService.showShoppings(userId, paging);
        Integer totalPages = shoppingListResult.getTotalPages();
        Long totalShoppings = shoppingListResult.getTotalElements();
        return ResponseEntity.ok()
                .header("x-total-pages", totalPages.toString())
                .header("x-total-count", totalShoppings.toString())
                .body(shoppingListResult.toList());
    }
}
