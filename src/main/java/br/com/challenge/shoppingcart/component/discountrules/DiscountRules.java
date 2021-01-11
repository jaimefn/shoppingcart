package br.com.challenge.shoppingcart.component.discountrules;

import br.com.challenge.shoppingcart.domain.Cart;

import java.math.BigDecimal;

public abstract class DiscountRules {
    protected final String TEN_PERCENT = "0.1";
    protected final String FIVE_PERCENT = "0.05";
    protected final String SEVEN_PERCENT = "0.07";

    protected DiscountRules nextDiscount;

    public DiscountRules(DiscountRules nextDiscount){
        this.nextDiscount = nextDiscount;
    }

    public abstract BigDecimal calculate(Cart cart);
}
