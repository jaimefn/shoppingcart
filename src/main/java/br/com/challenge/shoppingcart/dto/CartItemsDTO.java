package br.com.challenge.shoppingcart.dto;

import java.math.BigDecimal;

public class CartItemsDTO {
    private Long id;
    private CartDTO cart;
    private ProductDTO product;
    private Integer quantity;
    private BigDecimal unitValue;

    public CartItemsDTO(){}

    public CartItemsDTO(CartDTO cartDTO, ProductDTO productDTO, Integer quantity, BigDecimal unitValue ){
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

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
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
