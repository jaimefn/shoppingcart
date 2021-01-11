package br.com.challenge.shoppingcart.component.discountrules;

import br.com.challenge.shoppingcart.domain.Cart;

import java.math.BigDecimal;

public class NoDiscount extends DiscountRules {

    public NoDiscount(){
        super(null);
    }

    @Override
    public BigDecimal calculate(Cart cart) {
        return BigDecimal.ZERO;
    }
}