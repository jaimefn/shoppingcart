package br.com.challenge.shoppingcart.service;

import br.com.challenge.shoppingcart.dto.cart.CartDTO;
import br.com.challenge.shoppingcart.dto.cart.CartReqDTO;
import br.com.challenge.shoppingcart.dto.cartitems.CartItemsResDTO;
import br.com.challenge.shoppingcart.dto.product.ProductReqDTO;
import br.com.challenge.shoppingcart.dto.product.ProductResDTO;
import br.com.challenge.shoppingcart.dto.promocode.PromoCodeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        cartDTO = saveCart(new CartReqDTO());
    }

    @Test
    public void deveAplicarDescontoDe10PorCentoNoValorDoItemQueTiverQuantidadeMaiorQue9Items(){

       // assertEquals(valueOfDiscount,product.getValue().multiply(new BigDecimal("0.1")));
    }

    @Test
    public void deveSalvarUmCarrinhoCom3Items(){
        CartDTO cartDTO = getMockedCartDTO();
        CartDTO newCart = saveCart(new CartReqDTO());
        assertTrue(newCart != null);
    }

    @Test
    public void deveBuscarUmCarrinho(){
        final Long ID = cartDTO.getId();
        CartDTO cart = cartService.getById(ID);
        assertTrue(cart.getId().equals(ID));
    }

    private CartDTO saveCart(CartReqDTO cartDTO){
        return cartService.create(cartDTO);
    }

    private CartDTO getMockedCartDTO(){

        CartDTO cartDTO = new CartDTO();

        ProductResDTO product_A = productService.create(new ProductReqDTO("Product_A","Product A description",new BigDecimal(100)));
        ProductResDTO product_B = productService.create(new ProductReqDTO("Product_B","Product B description",new BigDecimal(200)));
        ProductResDTO product_C = productService.create(new ProductReqDTO("Product_C","Product C description",new BigDecimal(300)));

        List<CartItemsResDTO> cartItemsList = new ArrayList<>();
        CartItemsResDTO cartItems_A = new CartItemsResDTO();
        cartItems_A.setProduct(product_A);
        cartItems_A.setCart(cartDTO);
        cartItems_A.setQuantity(1);
        cartItems_A.setUnitValue(product_A.getValue());

        CartItemsResDTO cartItems_B = new CartItemsResDTO();
        cartItems_B.setProduct(product_B);
        cartItems_B.setCart(cartDTO);
        cartItems_B.setQuantity(5);
        cartItems_B.setUnitValue(product_B.getValue());

        CartItemsResDTO cartItems_C = new CartItemsResDTO();
        cartItems_C.setProduct(product_C);
        cartItems_C.setCart(cartDTO);
        cartItems_C.setQuantity(10);
        cartItems_C.setUnitValue(product_C.getValue());

        cartItemsList.add(cartItems_A);
        cartItemsList.add(cartItems_B);
        cartItemsList.add(cartItems_C);

        PromoCodeDTO promoCodeDTO = new PromoCodeDTO();

        cartDTO.setCartItems(cartItemsList);

        return cartDTO;
    }
}
