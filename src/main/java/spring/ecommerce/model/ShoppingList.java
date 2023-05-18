package spring.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ShoppingList {

    private String userName;

    private String productName;

    private Integer quantity;

    private Double totalPrice;

    private OffsetDateTime dateCreated;

    public ShoppingList(
            String userName,
            String productName,
            Integer quantity,
            Double totalPrice,
            OffsetDateTime dateCreated
            ) {
        this.userName = userName;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.dateCreated = dateCreated;
    }
}
