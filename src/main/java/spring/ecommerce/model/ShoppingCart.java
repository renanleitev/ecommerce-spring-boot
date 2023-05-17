package spring.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCart {

    private Integer quantity;

    private Double totalPrice;

    private Integer productId;

    private Integer userId;

}
