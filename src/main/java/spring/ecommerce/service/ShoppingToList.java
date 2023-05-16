package spring.ecommerce.service;

import spring.ecommerce.model.Shopping;

import java.util.List;

public class ShoppingToList {
    private List<Shopping> shoppings;
    public List<Shopping> getShopping() { return shoppings; }
    public void setShopping(List<Shopping> shoppingList) {
        this.shoppings = shoppingList;
    }
}
