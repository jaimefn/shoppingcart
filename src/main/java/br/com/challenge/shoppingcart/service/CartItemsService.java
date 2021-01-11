package br.com.challenge.shoppingcart.service;


import br.com.challenge.shoppingcart.domain.Cart;
import br.com.challenge.shoppingcart.domain.CartItems;
import br.com.challenge.shoppingcart.domain.Product;
import br.com.challenge.shoppingcart.repository.CartItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartItemsService extends BaseService {

    private CartItemsRepository cartItemsRepository;

    @Autowired
    public CartItemsService(CartItemsRepository cartItemsRepository){
        this.cartItemsRepository = cartItemsRepository;
    }

    public Optional<CartItems> findByCartIdAndProductId(Long cartId, Long ProductId){
        return cartItemsRepository.findByCartIdAndProductId(cartId,ProductId);
    }

}
