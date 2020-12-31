package br.com.challenge.shoppingcart.dto;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartDTO {
    private List<ProductDTO> products;
    private PromoCodeDTO promoCode;
    private BigDecimal totalValue;
    private BigDecimal totalValueWithDiscount;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public PromoCodeDTO getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(PromoCodeDTO promoCode) {
        this.promoCode = promoCode;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getTotalValueWithDiscount() {
        return totalValueWithDiscount;
    }

    public void setTotalValueWithDiscount(BigDecimal totalValueWithDiscount) {
        this.totalValueWithDiscount = totalValueWithDiscount;
    }
}
