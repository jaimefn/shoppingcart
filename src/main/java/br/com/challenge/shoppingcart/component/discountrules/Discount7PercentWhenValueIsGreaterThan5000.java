package br.com.challenge.shoppingcart.component.discountrules;

import br.com.challenge.shoppingcart.domain.Cart;

import java.math.BigDecimal;

public class Discount7PercentWhenValueIsGreaterThan5000 extends DiscountRules {

    public Discount7PercentWhenValueIsGreaterThan5000(DiscountRules nextDiscount){
        super(nextDiscount);
    }

    @Override
    public BigDecimal calculate(Cart cart) {
        if(isValueGreaterThan5000(cart.getTotalValueWithoutDiscount())){
            return apply7PercentDiscount(cart.getTotalValueWithoutDiscount());
        }
        return nextDiscount.calculate(cart);
    }

    private boolean isValueGreaterThan5000(BigDecimal value){
        return value.compareTo(new BigDecimal(5000)) >= 0;
    }

    private BigDecimal apply7PercentDiscount(BigDecimal value){
        return  value.multiply(new BigDecimal(SEVEN_PERCENT));
    }
}



