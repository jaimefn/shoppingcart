package br.com.challenge.shoppingcart.dto.cart;

import br.com.challenge.shoppingcart.dto.cartitems.CartItemsReqDTO;
import br.com.challenge.shoppingcart.dto.cartitems.CartItemsResDTO;
import br.com.challenge.shoppingcart.dto.promocode.PromoCodeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartReqDTO {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private PromoCodeDTO promoCode;
    private List<CartItemsReqDTO> cartItems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PromoCodeDTO getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(PromoCodeDTO promoCode) {
        this.promoCode = promoCode;
    }

    public List<CartItemsReqDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemsReqDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
