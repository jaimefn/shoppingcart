package br.com.challenge.shoppingcart.component.discountrules;

import br.com.challenge.shoppingcart.domain.Cart;
import br.com.challenge.shoppingcart.domain.CartItems;

import java.math.BigDecimal;

public class DiscountByQuantity10Items {
    protected final String TEN_PERCENT = "0.1";

    public BigDecimal calculate(CartItems item) {
        if(isQuantityGreaterThan10Units(item.getQuantity())){
            return apply10PercentDiscount(item.getUnitValue().multiply(new BigDecimal(item.getQuantity())));
        }

        return BigDecimal.ZERO;
    }
    private boolean isQuantityGreaterThan10Units(Integer value){
        return (value >= 10) ? true : false;
    }
    private BigDecimal apply10PercentDiscount(BigDecimal value){
        return  value.multiply(new BigDecimal(TEN_PERCENT));
    }
}
