package br.com.challenge.shoppingcart.service;

import br.com.challenge.shoppingcart.domain.Cart;
import br.com.challenge.shoppingcart.dto.CartDTO;
import br.com.challenge.shoppingcart.exceptions.CartNotFoundException;
import br.com.challenge.shoppingcart.exceptions.UserNotFoundException;
import br.com.challenge.shoppingcart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService extends BaseService {

    private CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    @Transactional
    public CartDTO create(CartDTO cartDTO){
        Cart cart = modelMapper.map(cartDTO,Cart.class);
        cart.setId(null);
        return modelMapper.map(save(cart),CartDTO.class);
    }

    @Transactional
    public CartDTO update(Long id, CartDTO cartDTO) {
        Cart cart = findById(id);
        modelMapper.map(cartDTO, cart);
        cart.setId(id);
        return modelMapper.map(save(cart),CartDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        Cart cart = findById(id);
        cart.setDeleted(true);
        save(cart);
    }

    @Transactional
    public CartDTO getById(Long id) {
        return modelMapper.map(findById(id),CartDTO.class);
    }

    private Cart save(Cart cart){
        return cartRepository.save(cart);
    }

    private Cart findById(Long id){
        return cartRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(CartNotFoundException::new);
    }
}
