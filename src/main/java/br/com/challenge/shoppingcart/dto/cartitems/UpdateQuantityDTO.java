package br.com.challenge.shoppingcart.dto.cartitems;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateQuantityDTO {
    @Min(0)
    @NotNull
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
