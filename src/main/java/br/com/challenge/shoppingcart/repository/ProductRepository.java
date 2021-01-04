package br.com.challenge.shoppingcart.repository;

import br.com.challenge.shoppingcart.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.OptionalDouble;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByIdAndDeletedFalse(Long id);
    Page<Product> findAllByDeletedFalse(Pageable pageable);
    Page<Product> findAllByTitleIsContainingAndDeletedFalse(String title, Pageable pageable);
}
