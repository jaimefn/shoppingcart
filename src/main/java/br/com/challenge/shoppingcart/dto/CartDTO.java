package br.com.challenge.shoppingcart.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartDTO {
    private Long id;
    private PromoCodeDTO promoCode;
    private BigDecimal totalValue= new BigDecimal(0);
    private List<CartItemsDTO> cartItems = new ArrayList<>();

    public CartDTO(){}

    public CartDTO(PromoCodeDTO promoCode, List<CartItemsDTO> cartItems){
        this.promoCode = promoCode;
        if(cartItems != null) {
            cartItems.forEach(c->c.setCart(this));
            this.cartItems = cartItems;
        }
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalValue() {
        cartItems.forEach(c->totalValue.add(c.getUnitValue().multiply(new BigDecimal(c.getQuantity()))));
        return totalValue;
    }

    public PromoCodeDTO getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(PromoCodeDTO promoCode) {
        this.promoCode = promoCode;
    }

    public List<CartItemsDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemsDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
