package br.com.challenge.shoppingcart.rest;

import br.com.challenge.shoppingcart.dto.product.ProductReqDTO;
import br.com.challenge.shoppingcart.service.ProductService;
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
@WebMvcTest(controllers = ProductResources.class)
public class ProductRestTest {
    private final String API_SUBSCRIPTON = "/api/product";
    private URI uri;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @BeforeEach
    public void setup() throws URISyntaxException {
        uri = new URI(API_SUBSCRIPTON);
    }

    @Test
    public void deveSalvarUmProduto_RetornarStatusCreated() throws Exception {
        String jsonToSend = Utils.parseObjToJson(getMockProductDTO());
        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(jsonToSend)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void deveFalharQuandoTitleForNuloOuVazio_RetornarBadRequest() throws Exception {
        URI uri = new URI(API_SUBSCRIPTON);
        ProductReqDTO productDTO = getMockProductDTO();
        productDTO.setTitle(null);
        String jsonToSend = Utils.parseObjToJson(productDTO);
        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(jsonToSend)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deveFalharQuandoValorForNulo_RetornarBadRequest() throws Exception {
        URI uri = new URI(API_SUBSCRIPTON);
        ProductReqDTO productDTO = getMockProductDTO();
        productDTO.setValue(null);
        String jsonToSend = Utils.parseObjToJson(productDTO);
        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(jsonToSend)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private ProductReqDTO getMockProductDTO(){
        return new ProductReqDTO("Title","Description",new BigDecimal(200));
    }
}
