package br.com.challenge.shoppingcart.dto.cart;

import br.com.challenge.shoppingcart.dto.cartitems.CartItemsResDTO;
import br.com.challenge.shoppingcart.dto.promocode.PromoCodeDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartDTO {
    private Long id;
    private PromoCodeDTO promoCode;
    private BigDecimal totalValueWithoutDiscount = BigDecimal.ZERO;
    private BigDecimal totalValueWithDiscount = BigDecimal.ZERO;
    private BigDecimal globalDiscountValue = BigDecimal.ZERO;
    private BigDecimal discountPercentage = BigDecimal.ZERO;
    private List<CartItemsResDTO> cartItems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalValueWithDiscount() {
        return totalValueWithDiscount;
    }

    public PromoCodeDTO getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(PromoCodeDTO promoCode) {
        this.promoCode = promoCode;
    }

    public List<CartItemsResDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemsResDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalValueWithoutDiscount() {
        return totalValueWithoutDiscount;
    }

    public void setTotalValueWithoutDiscount(BigDecimal totalValueWithoutDiscount) {
        this.totalValueWithoutDiscount = totalValueWithoutDiscount;
    }

    public BigDecimal getGlobalDiscountValue() {
        return globalDiscountValue;
    }

    public void setGlobalDiscountValue(BigDecimal globalDiscountValue) {
        this.globalDiscountValue = globalDiscountValue;
    }

    public void setTotalValueWithDiscount(BigDecimal totalValueWithDiscount) {
        this.totalValueWithDiscount = totalValueWithDiscount;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
