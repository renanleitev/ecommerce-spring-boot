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
    @Size(max = 255)
    private String quantity;

    @NotNull
    @Size(max = 255)
    private String totalPrice;

    private Long user;

    private Long product;

}
