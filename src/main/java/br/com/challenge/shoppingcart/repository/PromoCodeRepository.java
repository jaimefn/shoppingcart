package br.com.challenge.shoppingcart.repository;

import br.com.challenge.shoppingcart.domain.PromoCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {
    Page<PromoCode> findAllByDeletedFalse(Pageable pageable);
    Optional<PromoCode> findFirstByCodeAndDeletedFalse(String code);
    Optional<PromoCode> findByIdAndDeletedFalse(Long id);
}
