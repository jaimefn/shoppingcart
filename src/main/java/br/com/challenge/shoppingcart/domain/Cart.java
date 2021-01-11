package br.com.challenge.shoppingcart.domain;

import br.com.challenge.shoppingcart.component.discountrules.DiscountRules;
import br.com.challenge.shoppingcart.component.discountrules.Discounts;
import br.com.challenge.shoppingcart.dto.cart.CartDTO;
import br.com.challenge.shoppingcart.dto.cart.CartReqDTO;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart extends AbsctractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promo_code_id")
    private PromoCode promoCode;
    @Column(name = "total_value_with_discount")
    private BigDecimal totalValueWithDiscount = BigDecimal.ZERO;
    @Column(name = "total_value_without_discount")
    private BigDecimal totalValueWithoutDiscount = BigDecimal.ZERO;
    @Column(name = "global_discount_value")
    private BigDecimal globalDiscountValue = BigDecimal.ZERO;
    @Column(name = "discount_percentage")
    private BigDecimal discountPercentage = BigDecimal.ZERO;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<CartItems> cartItems = new ArrayList<>();

    public Cart(){}

    public Cart(ModelMapper mapper, CartReqDTO dto, DiscountRules discountRules){
        mapper.map(dto,this);
        recalculateValues(discountRules);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalValueWithDiscount() {

        return (this.totalValueWithDiscount == null) ? BigDecimal.ZERO : this.totalValueWithDiscount;
    }

    public void setTotalValueWithDiscount(BigDecimal totalValueWithDiscount) {
        this.totalValueWithDiscount = totalValueWithDiscount;
    }

    public PromoCode getPromoCode() {
        return (promoCode == null) ? new PromoCode() : this.promoCode;
    }

    public void setPromoCode(PromoCode promoCode) {
        this.promoCode = promoCode;
    }

    public List<CartItems> getCartItems() {
        return (cartItems == null) ? new ArrayList<>() : this.cartItems;
    }

    public void setCartItems(List<CartItems> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalValueWithoutDiscount() {
        return getItemsTotalValueWithoutDiscount();
    }

    public void setTotalValueWithoutDiscount(BigDecimal totalValueWithoutDiscount) {
        this.totalValueWithoutDiscount = totalValueWithoutDiscount;
    }

    public BigDecimal getGlobalDiscountValue() {
        return (this.globalDiscountValue != null) ? this.globalDiscountValue : BigDecimal.ZERO;
    }

    public void setGlobalDiscountValue(BigDecimal globalDiscountValue) {
        this.globalDiscountValue = globalDiscountValue;
    }

    public BigDecimal getDiscountPercentage() {
        return (this.discountPercentage != null) ? this.discountPercentage : BigDecimal.ZERO;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void addDiscountValue(BigDecimal discount) {
        if(this.globalDiscountValue == null) this.globalDiscountValue = BigDecimal.ZERO;
        this.globalDiscountValue = this.globalDiscountValue.add(discount);
    }

    public void addTotalValueWithoutDiscount(BigDecimal totalValue) {
        if(this.totalValueWithoutDiscount == null) this.totalValueWithoutDiscount = BigDecimal.ZERO;
        this.totalValueWithoutDiscount = this.totalValueWithoutDiscount.add(totalValue);
    }

    public void addProduct(Product product, CartItems item, DiscountRules discountRules) {
        if(!getCartItems().contains(item)){
            getCartItems().add(item);
        }
        item.increaseQuantity(product);
       recalculateValues(discountRules);
    }

    public void recalculateValues(DiscountRules discountRules){
        getCartItems().forEach(i->i.recalculateValues(i.getQuantity(),i.getProduct()));
        setTotalValueWithoutDiscount(getItemsTotalValueWithoutDiscount());
        BigDecimal discountPromoCodeValue = Discounts.getDiscountByPromoCode().calculate(this);
        setGlobalDiscountValue(discountRules.calculate(this).add(discountPromoCodeValue));
        setTotalValueWithDiscount(getTotalValueWithoutDiscount().subtract(getGlobalDiscountValue()));
        setDiscountPercentage(calcDiscountPercentage());
    }

    private CartItems getCartItemOrCreateOne(Product product) {
        return getCartItems().stream()
                .filter(item->item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(new CartItems(this,product));
    }

    private Boolean isProductAlreadyIntoCart(Product product) {
        return getCartItems().stream()
                .anyMatch(item->item.getProduct().getId().equals(product.getId()));
    }

    private BigDecimal getItemsTotalValueWithoutDiscount() {
        totalValueWithoutDiscount = BigDecimal.ZERO;
        if(this.cartItems != null && !this.cartItems.isEmpty()){
            totalValueWithoutDiscount = this.cartItems.stream().map(i->i.getTotalValueWithDiscount()).reduce(BigDecimal.ZERO,BigDecimal::add);
        }
        return totalValueWithoutDiscount;
    }

    private BigDecimal calcDiscountPercentage(){
        try{
            return getGlobalDiscountValue().divide(getTotalValueWithoutDiscount(), 2, RoundingMode.HALF_DOWN).multiply(new BigDecimal(100));
        }catch (Exception ex){}
        return BigDecimal.ZERO;
    }
}
