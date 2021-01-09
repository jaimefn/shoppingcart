package br.com.challenge.shoppingcart.service;

import br.com.challenge.shoppingcart.dto.PromoCodeDTO;
import br.com.challenge.shoppingcart.exceptions.PromoCodeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PromoCodeServiceTest {
    private PromoCodeDTO promoCodeDTO;
    private PromoCodeService promoCodeService;

    @Autowired
    public PromoCodeServiceTest(PromoCodeService promoCodeService){
        this.promoCodeService = promoCodeService;
    }

    @BeforeEach
    public void before(){
        promoCodeDTO = getMockedPromoCodeDTO();
        promoCodeDTO = savePromoCode(promoCodeDTO);
    }

    @Test
    public void deveSalvarUmPromoCode(){
        PromoCodeDTO promoCodeDTO = getMockedPromoCodeDTO();
        PromoCodeDTO newPromoCode = savePromoCode(promoCodeDTO);
        assertTrue(newPromoCode != null);
    }
    @Test
    public void deveAtualizarUmPromoCode(){
        final Integer NEW_QUANTITY = 20;
        PromoCodeDTO promoCodeDTO = getPromoCodeByCode("xpto");
        promoCodeDTO.setQuantity(NEW_QUANTITY);
        PromoCodeDTO promoCode = promoCodeService.update(promoCodeDTO.getId(), promoCodeDTO);
        assertEquals(NEW_QUANTITY, promoCode.getQuantity());
    }
    @Test
    public void deveDeletarUmPromoCode(){
        promoCodeService.delete(promoCodeDTO.getId());
        assertThrows(PromoCodeNotFoundException.class,()->{getPromoCodeById(promoCodeDTO.getId());});
    }
    @Test
    public void deveBuscarUmPromoCode(){
        String CODE = "xpto";
        PromoCodeDTO promoCodeDTO = getPromoCodeByCode(CODE);
        assertEquals(CODE, promoCodeDTO.getCode());
    }

    private PromoCodeDTO savePromoCode(PromoCodeDTO promoCodeDTO){
        return promoCodeService.create(promoCodeDTO);
    }

    private PromoCodeDTO getPromoCodeByCode(String code){
        PromoCodeDTO promoCodeDTO = promoCodeService.getByCode(code);
        checkIfPromoCodeExist(promoCodeDTO);
        return promoCodeDTO;
    }

    private PromoCodeDTO getPromoCodeById(Long id){
        PromoCodeDTO promoCodeDTO = promoCodeService.getById(id);
        checkIfPromoCodeExist(promoCodeDTO);
        return promoCodeDTO;
    }

    private void checkIfPromoCodeExist(PromoCodeDTO promoCodeDTO) {
        if(promoCodeDTO == null){
            throw new PromoCodeNotFoundException();
        }
    }

    private PromoCodeDTO getMockedPromoCodeDTO(){
        PromoCodeDTO promoCodeDTO = new PromoCodeDTO();
        promoCodeDTO.setId(null);
        promoCodeDTO.setCode("xpto");
        promoCodeDTO.setDescription("Description");
        promoCodeDTO.setDiscountPercentage(new BigDecimal(200));
        promoCodeDTO.setQuantity(10);
        return promoCodeDTO;
    }
}
