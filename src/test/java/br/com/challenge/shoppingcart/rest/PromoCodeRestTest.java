package br.com.challenge.shoppingcart.rest;

import br.com.challenge.shoppingcart.dto.promocode.PromoCodeDTO;
import br.com.challenge.shoppingcart.service.PromoCodeService;
import br.com.challenge.shoppingcart.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PromoCodeRest.class)
public class PromoCodeRestTest {
    private final String API_SUBSCRIPTON = "/api/promo-code";
    private URI uri;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PromoCodeService promoCodeService;

    @BeforeEach
    public void setup() throws URISyntaxException {
        uri = new URI(API_SUBSCRIPTON);
    }

    @Test
    public void deveFalharQuandoCodeForNuloOuVazio_RetornarBadRequest() throws Exception {
        URI uri = new URI(API_SUBSCRIPTON);
        PromoCodeDTO promoCodeDTO = getMockPromoCodeDTO();
        promoCodeDTO.setCode(" ");
        String jsonToSend = Utils.parseObjToJson(promoCodeDTO);
        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(jsonToSend)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deveFalharQuandoDescontoForNulo_RetornarBadRequest() throws Exception {
        URI uri = new URI(API_SUBSCRIPTON);
        PromoCodeDTO promoCodeDTO = getMockPromoCodeDTO();
        promoCodeDTO.setDiscountPercentage(null);
        String jsonToSend = Utils.parseObjToJson(promoCodeDTO);
        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(jsonToSend)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deveSalvarUmPromoCode_RetornarStatusOk() throws Exception {
        URI uri = new URI(API_SUBSCRIPTON);
        PromoCodeDTO promoCodeDTO = getMockPromoCodeDTO();
        String jsonToSend = Utils.parseObjToJson(promoCodeDTO);
        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(jsonToSend)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    private PromoCodeDTO getMockPromoCodeDTO(){
        PromoCodeDTO promoCodeDTO = new PromoCodeDTO();
        promoCodeDTO.setId(null);
        promoCodeDTO.setDescription("description");
        promoCodeDTO.setCode("CodeXPTO");
        promoCodeDTO.setDiscountPercentage(new BigDecimal(50));
        promoCodeDTO.setQuantity(10);
        return promoCodeDTO;
    }
}
