package br.com.challenge.shoppingcart.component.discountrules;

public abstract class Discounts {
    public static DiscountRules getChainOfDiscountRules(){
        Discount10PercentWhenValueIsGreaterThan10000 discountA;
        Discount7PercentWhenValueIsGreaterThan5000 discountB;
        Discount5PercentWhenValueIsGreaterThan1000 discountC;
        discountC = new Discount5PercentWhenValueIsGreaterThan1000(new NoDiscount());
        discountB = new Discount7PercentWhenValueIsGreaterThan5000(discountC);
        discountA = new Discount10PercentWhenValueIsGreaterThan10000(discountB);
        DiscountRules discountRules = discountA;
        return discountRules;
    }
    public static DiscountByQuantity10Items getDiscountByQuantity10Items(){
        return new DiscountByQuantity10Items();
    }
    public static DiscountByPromoCode getDiscountByPromoCode(){
        return new DiscountByPromoCode();
    }
}
