package br.com.challenge.shoppingcart.repository;

import br.com.challenge.shoppingcart.domain.Cart;
import br.com.challenge.shoppingcart.domain.CartItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    Optional<CartItems> findByCartIdAndProductId(Long cartId, Long productId);
}
