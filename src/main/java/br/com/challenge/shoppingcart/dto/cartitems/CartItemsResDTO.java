package br.com.challenge.shoppingcart.dto.cartitems;

import br.com.challenge.shoppingcart.dto.cart.CartDTO;
import br.com.challenge.shoppingcart.dto.product.ProductReqDTO;
import br.com.challenge.shoppingcart.dto.product.ProductResDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class CartItemsResDTO {
    private Long id;
    @JsonIgnore
    private CartDTO cart;
    private ProductResDTO product;
    private Integer quantity;
    private BigDecimal unitValue;

    public CartItemsResDTO(){}

    public CartItemsResDTO(CartDTO cartDTO, ProductResDTO productDTO, Integer quantity, BigDecimal unitValue ){
        this.cart = cartDTO;
        this.product = productDTO;
        this.quantity = quantity;
        this.unitValue = unitValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartDTO getCart() {
        return cart;
    }

    public void setCart(CartDTO cart) {
        this.cart = cart;
    }

    public ProductResDTO getProduct() {
        return product;
    }

    public void setProduct(ProductResDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }
}
