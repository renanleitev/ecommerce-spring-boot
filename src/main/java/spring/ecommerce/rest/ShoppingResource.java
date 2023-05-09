package spring.ecommerce.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.ecommerce.model.ShoppingDTO;
import spring.ecommerce.service.ShoppingService;


@RestController
@RequestMapping(value = "/api/shoppings", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShoppingResource {

    private final ShoppingService shoppingService;

    public ShoppingResource(final ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingDTO>> getAllShoppings() {
        return ResponseEntity.ok(shoppingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingDTO> getShopping(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(shoppingService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createShopping(@RequestBody @Valid final ShoppingDTO shoppingDTO) {
        final Long createdId = shoppingService.create(shoppingDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateShopping(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final ShoppingDTO shoppingDTO) {
        shoppingService.update(id, shoppingDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteShopping(@PathVariable(name = "id") final Long id) {
        shoppingService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
