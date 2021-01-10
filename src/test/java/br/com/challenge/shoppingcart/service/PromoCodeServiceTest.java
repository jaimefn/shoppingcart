package br.com.challenge.shoppingcart.service;

import br.com.challenge.shoppingcart.dto.promocode.PromoCodeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Random;

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
        PromoCodeDTO mokedPromoCode = getMockedPromoCodeDTO();
        promoCodeDTO = savePromoCode(mokedPromoCode);
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
        PromoCodeDTO oldPromoCodeDTO = getPromoCodeByCode(promoCodeDTO.getCode());
        oldPromoCodeDTO.setQuantity(NEW_QUANTITY);
        PromoCodeDTO updatedPromoCode = promoCodeService.update(promoCodeDTO.getId(), oldPromoCodeDTO);
        assertEquals(NEW_QUANTITY, updatedPromoCode.getQuantity());
    }
    @Test
    public void deveDeletarUmPromoCode(){
        promoCodeService.delete(promoCodeDTO.getId());
        assertThrows(IllegalArgumentException.class,()->{getPromoCodeById(promoCodeDTO.getId());});
    }
    @Test
    public void deveBuscarUmPromoCode(){
        String CODE = promoCodeDTO.getCode();
        PromoCodeDTO foundedPromoCode = getPromoCodeByCode(CODE);
        assertEquals(CODE, foundedPromoCode.getCode());
    }

    private PromoCodeDTO savePromoCode(PromoCodeDTO promoCodeDTO){
        return promoCodeService.create(promoCodeDTO);
    }

    private PromoCodeDTO getPromoCodeByCode(String code){
        return promoCodeService.getByCode(code);
    }

    private PromoCodeDTO getPromoCodeById(Long id){
       return promoCodeService.getById(id);
    }

    private PromoCodeDTO getMockedPromoCodeDTO(){
        Random rd = new Random();
        String code = String.valueOf(rd.nextInt(1000));
        PromoCodeDTO promoCodeDTO = new PromoCodeDTO();
        promoCodeDTO.setId(null);
        promoCodeDTO.setCode(code);
        promoCodeDTO.setDescription("Description");
        promoCodeDTO.setDiscountPercentage(new BigDecimal(20));
        promoCodeDTO.setQuantity(10);
        return promoCodeDTO;
    }
}
