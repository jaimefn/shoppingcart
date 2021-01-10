package br.com.challenge.shoppingcart.service;

import br.com.challenge.shoppingcart.domain.Cart;
import br.com.challenge.shoppingcart.domain.CartItems;
import br.com.challenge.shoppingcart.domain.Product;
import br.com.challenge.shoppingcart.domain.PromoCode;
import br.com.challenge.shoppingcart.dto.cart.CartDTO;
import br.com.challenge.shoppingcart.dto.cartitems.AddProductDTO;
import br.com.challenge.shoppingcart.dto.cartitems.UpdateQuantityDTO;
import br.com.challenge.shoppingcart.dto.promocode.PromoCodeApplyDTO;
import br.com.challenge.shoppingcart.dto.promocode.PromoCodeDTO;
import br.com.challenge.shoppingcart.exceptions.CartNotFoundException;
import br.com.challenge.shoppingcart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService extends BaseService {

    private CartRepository cartRepository;
    private ProductService productService;
    private PromoCodeService promoCodeService;

    @Autowired
    public CartService(CartRepository cartRepository,
                       ProductService productService,
                       PromoCodeService promoCodeService){
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.promoCodeService = promoCodeService;
    }

    @Transactional
    public CartDTO create(CartDTO cartDTO){
        Cart cart = modelMapper.map(cartDTO,Cart.class);
        cart.setId(null);
        return modelMapper.map(cartRepository.save(cart),CartDTO.class);
    }

    @Transactional
    public CartDTO update(Long id, CartDTO cartDTO) {
        Cart cart = findById(id);
        modelMapper.map(cartDTO, cart);
        cart.setId(id);
        return modelMapper.map(cartRepository.save(cart),CartDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        Cart cart = findById(id);
        cart.setDeleted(true);
        cartRepository.save(cart);
    }

    @Transactional
    public CartDTO getById(Long id) {
        return modelMapper.map(findById(id),CartDTO.class);
    }
    @Transactional
    public CartDTO addProductToCart(Long cartId, AddProductDTO addProductDTO) {
        Cart cart = findById(cartId);
        Product product = productService.getById(addProductDTO.getProductId());
        CartItems newProduct = new CartItems(cart, product, 1);
        cart.getCartItems().add(newProduct);
        return modelMapper.map(cartRepository.save(cart),CartDTO.class);
    }

    @Transactional
    public CartDTO updateQuantityItemCart(Long cartId, Long itemId, UpdateQuantityDTO dto) {
        Cart cart = findById(cartId);
        cart.getCartItems().stream().filter(i->i.getId().equals(itemId)).findFirst().ifPresent(i->i.setQuantity(dto.getQuantity()));
        return modelMapper.map(cartRepository.save(cart),CartDTO.class);
    }

    @Transactional
    public CartDTO removeItemCart(Long cartId, Long itemId) {
        Cart cart = findById(cartId);
        CartItems itemToRemove = cart.getCartItems().stream().filter(i->i.getId().equals(itemId)).findFirst().orElseThrow(IllegalArgumentException::new);
        cart.getCartItems().remove(itemToRemove);
        return modelMapper.map(cartRepository.save(cart),CartDTO.class);
    }

    @Transactional
    public CartDTO applyPromoCode(Long cartId, PromoCodeApplyDTO dto) {
        Cart cart = findById(cartId);
        PromoCode promoCode = modelMapper.map(promoCodeService.getByCode(dto.getCode()),PromoCode.class);
        cart.setPromoCode(promoCode);
        return modelMapper.map(cartRepository.save(cart),CartDTO.class);
    }

    @Transactional
    public CartDTO removePromoCode(Long cartId, Long promocodeId) {
        Cart cart = findById(cartId);
        cart.setPromoCode(null);
        return modelMapper.map(cartRepository.save(cart),CartDTO.class);
    }

    private Cart findById(Long id){
        return cartRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(CartNotFoundException::new);
    }
}
