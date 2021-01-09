package br.com.challenge.shoppingcart.service;

import br.com.challenge.shoppingcart.domain.CartItems;
import br.com.challenge.shoppingcart.dto.*;
import br.com.challenge.shoppingcart.exceptions.CartNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartServiceTest {
    private ProductDTO product_A;
    private ProductDTO product_B;
    private ProductDTO product_C;
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
        createProducts();
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
    public void deveDeletarUmCarrinho(){

        cartService.delete(cartDTO.getId());
        assertThrows(CartNotFoundException.class,()-> getCartById(1L));
    }
    @Test
    public void deveBuscarUmCarrinho(){
        Long ID = cartDTO.getId();
        CartDTO cart = getCartById(ID);
        assertTrue(cart.getId().equals(ID));
    }

    private CartDTO saveCart(CartDTO cartDTO){
        return cartService.create(cartDTO);
    }

    private CartDTO getCartById(Long id){
        CartDTO cart = cartService.getById(id);
        checkIfCartExist(cart);
        return cart;
    }

    private CartDTO getMockedCartDTO(){

        CartDTO cartDTO = new CartDTO();

        List<CartItemsDTO> cartItemsList = new ArrayList<>();
        CartItemsDTO cartItems_A = new CartItemsDTO(cartDTO,product_A,1,product_A.getValue());
        CartItemsDTO cartItems_B = new CartItemsDTO(cartDTO,product_B,1,product_B.getValue());
        CartItemsDTO cartItems_C = new CartItemsDTO(cartDTO,product_C,1,product_C.getValue());
        cartItemsList.add(cartItems_A);
        cartItemsList.add(cartItems_B);
        cartItemsList.add(cartItems_C);

        PromoCodeDTO promoCodeDTO = new PromoCodeDTO();

        cartDTO.setCartItems(cartItemsList);

        return cartDTO;
    }

    private void createProducts(){
        product_A = new ProductDTO("Product_A","Product A description",new BigDecimal(100));
        product_B = new ProductDTO("Product_B","Product B description",new BigDecimal(200));
        product_C = new ProductDTO("Product_C","Product C description",new BigDecimal(300));
        product_A = productService.create(product_A);
        product_B = productService.create(product_B);
        product_C = productService.create(product_C);
    }
    private void checkIfCartExist(CartDTO cart){
        if(cart == null){
            throw new CartNotFoundException();
        }
    }

}
