package br.com.challenge.shoppingcart.service;

import br.com.challenge.shoppingcart.dto.cart.CartDTO;
import br.com.challenge.shoppingcart.dto.cartitems.CartItemsResDTO;
import br.com.challenge.shoppingcart.dto.product.ProductReqDTO;
import br.com.challenge.shoppingcart.dto.product.ProductResDTO;
import br.com.challenge.shoppingcart.dto.promocode.PromoCodeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartServiceTest {
    private CartDTO cartDTO;

    private CartService cartService;
    private ProductService productService;

    @Autowired
    public CartServiceTest(CartService cartService, ProductService productService){
        this.cartService = cartService;
        this.productService = productService;
    }

    @BeforeEach
    public void before(){
        cartDTO = getMockedCartDTO();
        cartDTO = saveCart(cartDTO);
    }

    @Test
    public void deveSalvarUmCarrinhoCom3Items(){
        CartDTO cartDTO = getMockedCartDTO();
        CartDTO newCart = saveCart(cartDTO);
        assertTrue(newCart != null);
        assertTrue(newCart.getCartItems().size() == 3);
    }

    @Test
    public void deveBuscarUmCarrinho(){
        final Long ID = cartDTO.getId();
        CartDTO cart = cartService.getById(ID);
        assertTrue(cart.getId().equals(ID));
    }

    private CartDTO saveCart(CartDTO cartDTO){
        return cartService.create(cartDTO);
    }

    private CartDTO getMockedCartDTO(){

        CartDTO cartDTO = new CartDTO();

        ProductResDTO product_A = productService.create(new ProductReqDTO("Product_A","Product A description",new BigDecimal(100)));
        ProductResDTO product_B = productService.create(new ProductReqDTO("Product_B","Product B description",new BigDecimal(200)));
        ProductResDTO product_C = productService.create(new ProductReqDTO("Product_C","Product C description",new BigDecimal(300)));

        List<CartItemsResDTO> cartItemsList = new ArrayList<>();
        CartItemsResDTO cartItems_A = new CartItemsResDTO(cartDTO,product_A,1,product_A.getValue());
        CartItemsResDTO cartItems_B = new CartItemsResDTO(cartDTO,product_B,1,product_B.getValue());
        CartItemsResDTO cartItems_C = new CartItemsResDTO(cartDTO,product_C,1,product_C.getValue());
        cartItemsList.add(cartItems_A);
        cartItemsList.add(cartItems_B);
        cartItemsList.add(cartItems_C);

        PromoCodeDTO promoCodeDTO = new PromoCodeDTO();

        cartDTO.setCartItems(cartItemsList);

        return cartDTO;
    }
}
