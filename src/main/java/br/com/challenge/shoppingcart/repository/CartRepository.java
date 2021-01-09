package br.com.challenge.shoppingcart.repository;

import br.com.challenge.shoppingcart.domain.Cart;
import br.com.challenge.shoppingcart.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Page<Cart> findAllByDeletedFalse(Pageable pageable);
    Optional<Cart> findByIdAndDeletedFalse(Long id);
}
