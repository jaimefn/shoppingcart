package br.com.challenge.shoppingcart.rest;

import br.com.challenge.shoppingcart.dto.CartDTO;
import br.com.challenge.shoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    @RequestMapping(value = "/cart/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCart(@Valid @RequestBody CartDTO cartDTO, @PathVariable Long id){
        return new ResponseEntity<>(cartService.update(id, cartDTO), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/cart/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCart(@PathVariable Long id){
        cartService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/cart/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdCart(@PathVariable Long id){
        return new ResponseEntity<>(cartService.getById(id), HttpStatus.OK);
    }
}
