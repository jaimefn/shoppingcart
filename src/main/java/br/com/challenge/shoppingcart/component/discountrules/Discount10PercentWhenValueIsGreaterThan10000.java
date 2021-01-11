package br.com.challenge.shoppingcart.component.discountrules;

import br.com.challenge.shoppingcart.domain.Cart;

import java.math.BigDecimal;

public class Discount10PercentWhenValueIsGreaterThan10000  extends DiscountRules {

    public Discount10PercentWhenValueIsGreaterThan10000(DiscountRules nextDiscount){
        super(nextDiscount);
    }

    @Override
    public BigDecimal calculate(Cart cart) {
        if(isValueGreaterThan10000(cart.getTotalValueWithoutDiscount())){
            return apply10PercentDiscount(cart.getTotalValueWithoutDiscount());
        }
        return nextDiscount.calculate(cart);
    }

    private boolean isValueGreaterThan10000(BigDecimal value){
        return value.compareTo(new BigDecimal(10000)) >= 0;
    }

    private BigDecimal apply10PercentDiscount(BigDecimal value){
        return  value.multiply(new BigDecimal(TEN_PERCENT));
    }
}