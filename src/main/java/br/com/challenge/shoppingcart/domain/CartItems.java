package br.com.challenge.shoppingcart.domain;

import br.com.challenge.shoppingcart.component.discountrules.Discounts;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
public class CartItems extends AbsctractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;
    @Column(name = "unit_value")
    private BigDecimal unitValue;
    @Column(name = "item_discount_value")
    private BigDecimal itemDiscountValue;
    @Column(name = "total_value_with_discount")
    private BigDecimal totalValueWithDiscount;
    @Column(name = "total_value_without_discount")
    private BigDecimal totalValueWithoutDiscount;

    public CartItems(){}

    public CartItems(Cart cart, Product product){
        setCart(cart);
        setProduct(product);
        recalculateValues(0, product);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return (quantity == null) ? 0 : quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    public BigDecimal getItemDiscountValue() {
        return itemDiscountValue;
    }

    public void setItemDiscountValue(BigDecimal itemDiscountValue) {
        this.itemDiscountValue = itemDiscountValue;
    }

    public BigDecimal getTotalValueWithDiscount() {
        return totalValueWithDiscount;
    }

    public void setTotalValueWithDiscount(BigDecimal totalValueWithDiscount) {
        this.totalValueWithDiscount = totalValueWithDiscount;
    }

    public BigDecimal getTotalValueWithoutDiscount() {
        return totalValueWithoutDiscount;
    }

    public void setTotalValueWithoutDiscount(BigDecimal totalValueWithoutDiscount) {
        this.totalValueWithoutDiscount = totalValueWithoutDiscount;
    }

    public Integer increaseQuantity(Product product) {
        Integer quantity = getQuantity() + 1;
        setQuantity(quantity);
        recalculateValues(getQuantity(),product);
        return quantity;
    }
    public Integer decreaseQuantity(Product product) {
        if(getQuantity() > 0) {
            Integer quantity = getQuantity() - 1;
            setQuantity(quantity);
        }
        recalculateValues(getQuantity(),product);
        return getQuantity();
    }

    public void recalculateValues(Integer quantity, Product product){
        this.quantity = quantity;
        this.unitValue = product.getValue();
        this.itemDiscountValue = Discounts.getDiscountByQuantity10Items().calculate(this);
        this.totalValueWithoutDiscount = unitValue.multiply(new BigDecimal(quantity));
        this.totalValueWithDiscount = totalValueWithoutDiscount.subtract(itemDiscountValue);

    }
}
