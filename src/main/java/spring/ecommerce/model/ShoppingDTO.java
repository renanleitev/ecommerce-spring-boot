package spring.ecommerce.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ShoppingDTO {

    private Long id;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double totalPrice;

    private Long user;

    private Long product;

}
