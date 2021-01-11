package br.com.challenge.shoppingcart.component.discountrules;

import br.com.challenge.shoppingcart.domain.Cart;
import br.com.challenge.shoppingcart.domain.PromoCode;

import java.math.BigDecimal;

public class DiscountByPromoCode {

    public BigDecimal calculate(Cart cart) {
        if(hasPromoCodeApplied(cart.getPromoCode())){
            return  calculateDiscountAccordingToThePromoCodeRule(cart);
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal calculateDiscountAccordingToThePromoCodeRule(Cart cart){
        BigDecimal convertValueToFraction = cart.getPromoCode().getDiscountPercentage().divide(new BigDecimal(100));
        return  cart.getTotalValueWithoutDiscount().multiply(convertValueToFraction);
    }

    private boolean hasPromoCodeApplied(PromoCode promoCode){
        return (promoCode != null)? true : false;
    }
}