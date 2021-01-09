package br.com.challenge.shoppingcart.rest;

import br.com.challenge.shoppingcart.dto.CartDTO;
import br.com.challenge.shoppingcart.dto.CustomerDTO;
import br.com.challenge.shoppingcart.service.CartService;
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
@WebMvcTest(controllers = CartRest.class)
public class CartRestTest {
    private final String API_SUBSCRIPTON = "/api/cart";
    private URI uri;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CartService cartService;

    @BeforeEach
    public void setup() throws URISyntaxException {
        uri = new URI(API_SUBSCRIPTON);
    }

    @Test
    public void deveSalvarUmCarrinho_RetornarStatusCreated() throws Exception {
        String jsonToSend = Utils.parseObjToJson(getMockCartDTO());
        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(jsonToSend)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private CartDTO getMockCartDTO(){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(null);
        cartDTO.setPromoCode(null);
        return cartDTO;
    }
}
