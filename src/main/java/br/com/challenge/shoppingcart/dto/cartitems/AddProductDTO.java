package br.com.challenge.shoppingcart.dto.cartitems;

import javax.validation.constraints.NotNull;

public class AddProductDTO {
    @NotNull
    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
