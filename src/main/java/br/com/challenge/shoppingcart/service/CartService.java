package br.com.challenge.shoppingcart.service;

import br.com.challenge.shoppingcart.component.discountrules.Discounts;
import br.com.challenge.shoppingcart.domain.Cart;
import br.com.challenge.shoppingcart.domain.CartItems;
import br.com.challenge.shoppingcart.domain.Product;
import br.com.challenge.shoppingcart.domain.PromoCode;
import br.com.challenge.shoppingcart.dto.cart.CartDTO;
import br.com.challenge.shoppingcart.dto.cart.CartReqDTO;
import br.com.challenge.shoppingcart.dto.cartitems.AddProductDTO;
import br.com.challenge.shoppingcart.dto.cartitems.UpdateQuantityDTO;
import br.com.challenge.shoppingcart.dto.promocode.PromoCodeApplyDTO;
import br.com.challenge.shoppingcart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService extends BaseService {

    private CartRepository cartRepository;
    private ProductService productService;
    private PromoCodeService promoCodeService;
    private CartItemsService cartItemsService;

    @Autowired
    public CartService(CartRepository cartRepository,
                       ProductService productService,
                       PromoCodeService promoCodeService,
                       CartItemsService cartItemsService){
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.promoCodeService = promoCodeService;
        this.cartItemsService = cartItemsService;
    }

    @Transactional
    public CartDTO create(CartReqDTO cartDTO){
        Cart cart = new Cart(modelMapper, cartDTO,Discounts.getChainOfDiscountRules());
        cart.setId(null);
        cart.setPromoCode(null);
        cart.setCartItems(null);
        return updateCart(cart);
    }

    @Transactional
    public CartDTO update(Long id, CartDTO cartDTO) {
        Cart cart = findById(id);
        modelMapper.map(cartDTO, cart);
        cart.setId(id);
        return updateCart(cart);
    }

    @Transactional
    public void delete(Long id) {
        Cart cart = findById(id);
        cart.setDeleted(true);
        cartRepository.save(cart);
    }

    @Transactional
    public CartDTO getById(Long id) {
        return updateCart(findById(id));

    }

    @Transactional
    public CartDTO addProductToCart(Long cartId, AddProductDTO addProductDTO) {
        Cart cart = findById(cartId);
        Product product = productService.getById(addProductDTO.getProductId());
        CartItems item = cartItemsService.findByCartIdAndProductId(cartId, product.getId()).orElse(new CartItems(cart,product));
        cart.addProduct(product, item, Discounts.getChainOfDiscountRules());
        return updateCart(cart);
    }

    @Transactional
    public CartDTO updateQuantityItemCart(Long cartId, Long itemId, UpdateQuantityDTO dto) {
        Cart cart = findById(cartId);
        cart.getCartItems().stream().filter(i->i.getId().equals(itemId)).findFirst().ifPresent(i->i.setQuantity(dto.getQuantity()));
        return updateCart(cart);
    }

    @Transactional
    public CartDTO removeItemCart(Long cartId, Long itemId) {
        Cart cart = findById(cartId);
        CartItems itemToRemove = cart.getCartItems().stream().filter(i->i.getId().equals(itemId)).findFirst().orElseThrow(()->new IllegalArgumentException("error.cartitem.not.found"));
        cart.getCartItems().remove(itemToRemove);
        return updateCart(cart);
    }

    @Transactional
    public CartDTO applyPromoCode(Long cartId, PromoCodeApplyDTO dto) {
        Cart cart = findById(cartId);
        PromoCode oldPromoCode = cart.getPromoCode();
        PromoCode newPromoCode = modelMapper.map(promoCodeService.getByCode(dto.getCode()),PromoCode.class);
        checkIfNewPromoCodeHasMoreDiscountThanOldPromoCode(oldPromoCode, newPromoCode);
        cart.setPromoCode(newPromoCode);
        return updateCart(cart);
    }

    private void checkIfNewPromoCodeHasMoreDiscountThanOldPromoCode(PromoCode oldPromoCode, PromoCode newPromoCode) {
        if(oldPromoCode !=null && oldPromoCode.getDiscountPercentage().compareTo(newPromoCode.getDiscountPercentage()) >= 0){
            throw new IllegalArgumentException("error.promocode.has.value.less.than.actual");
        }
    }

    @Transactional
    public CartDTO removePromoCode(Long cartId, Long promocodeId) {
        Cart cart = findById(cartId);
        cart.setPromoCode(null);
        return updateCart(cart);
    }

    private Cart findById(Long id){
        return cartRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(()->new IllegalArgumentException("error.cart.not.found"));
    }
    private CartDTO updateCart(Cart cart){
        cart.recalculateValues(Discounts.getChainOfDiscountRules());
        return modelMapper.map(cartRepository.save(cart),CartDTO.class);
    }
}
