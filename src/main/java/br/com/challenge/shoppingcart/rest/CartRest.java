package br.com.challenge.shoppingcart.rest;

import br.com.challenge.shoppingcart.dto.cart.CartDTO;
import br.com.challenge.shoppingcart.dto.cartitems.AddProductDTO;
import br.com.challenge.shoppingcart.dto.cartitems.UpdateQuantityDTO;
import br.com.challenge.shoppingcart.dto.promocode.PromoCodeApplyDTO;
import br.com.challenge.shoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class CartRest {

    private CartService cartService;

    @Autowired
    public CartRest(CartService cartService){
        this.cartService = cartService;
    }

    @RequestMapping(value = "/cart",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCart(@Valid @RequestBody CartDTO cartDTO){
        return new ResponseEntity<>(cartService.create(cartDTO), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cart/{cartId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdCart(@PathVariable Long cartId){
        return new ResponseEntity<>(cartService.getById(cartId), HttpStatus.OK);
    }

    @RequestMapping(value = "/cart/{cartId}/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addItemCart(@Valid @RequestBody AddProductDTO addProductDTO, @PathVariable Long cartId){
        return new ResponseEntity<>(cartService.addProductToCart(cartId, addProductDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/cart/{cartId}/item-cart/{itemId}/update-quantity",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateQuantityItemCart(@Valid @RequestBody UpdateQuantityDTO updateQuantityDTO, @PathVariable Long cartId, @PathVariable Long itemId){
        return new ResponseEntity<>(cartService.updateQuantityItemCart(cartId, itemId, updateQuantityDTO), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cart/{cartId}/item-cart/{itemId}/remove",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeItemCart(@PathVariable Long cartId, @PathVariable Long itemId){
        return new ResponseEntity<>(cartService.removeItemCart(cartId, itemId), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cart/{cartId}/promo-code/apply",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> applyPromoCode(@RequestBody PromoCodeApplyDTO promoCodeApplyDTO, @PathVariable Long cartId){
        return new ResponseEntity<>(cartService.applyPromoCode(cartId, promoCodeApplyDTO), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cart/{cartId}/promo-code/{promocodeId}/remove",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removePromoCode(@PathVariable Long cartId, @PathVariable Long promocodeId){
        return new ResponseEntity<>(cartService.removePromoCode(cartId, promocodeId), HttpStatus.CREATED);
    }
}
