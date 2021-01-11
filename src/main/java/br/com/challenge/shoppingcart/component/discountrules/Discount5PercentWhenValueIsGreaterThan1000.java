package br.com.challenge.shoppingcart.component.discountrules;

import br.com.challenge.shoppingcart.domain.Cart;

import java.math.BigDecimal;

public class Discount5PercentWhenValueIsGreaterThan1000 extends DiscountRules {

    public Discount5PercentWhenValueIsGreaterThan1000(DiscountRules nextDiscount){
        super(nextDiscount);
    }

    @Override
    public BigDecimal calculate(Cart cart) {
        if(isValueGreaterThan1000(cart.getTotalValueWithoutDiscount())){
            return apply5PercentDiscount(cart.getTotalValueWithoutDiscount());
        }
        return nextDiscount.calculate(cart);
    }

    private boolean isValueGreaterThan1000(BigDecimal value){
        return value.compareTo(new BigDecimal(1000)) >= 0;
    }

    private BigDecimal apply5PercentDiscount(BigDecimal value){
        return  value.multiply(new BigDecimal(FIVE_PERCENT));
    }
}