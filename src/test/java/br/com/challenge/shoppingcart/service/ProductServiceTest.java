package br.com.challenge.shoppingcart.service;

import br.com.challenge.shoppingcart.dto.product.ProductReqDTO;
import br.com.challenge.shoppingcart.dto.product.ProductResDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {

    private ProductService productService;

    @Autowired
    public ProductServiceTest(ProductService productService){
        this.productService = productService;
    }

    @BeforeEach
    public void before(){
        ProductReqDTO productDTO = getMockedProductDTO();
        saveProduct(productDTO);
    }

    @Test
    public void deveSalvarUmProduto(){
        ProductReqDTO productDTO = getMockedProductDTO();
        productDTO.setTitle("newProduct");
        ProductResDTO newProduct = saveProduct(productDTO);
        assertTrue(newProduct != null);
    }
    @Test
    public void deveAtualizarUmProduto(){
        final String OLD_TITLE = "Title";
        final String NEW_TITLE = "Title2";
        ProductResDTO oldProductDTO = getProductByTitle(OLD_TITLE);
        ProductReqDTO updatedProduct = new ProductReqDTO(oldProductDTO.getTitle(),oldProductDTO.getDescription(),oldProductDTO.getValue());
        updatedProduct.setTitle(NEW_TITLE);
        ProductResDTO product = productService.update(oldProductDTO.getId(), updatedProduct);
        assertEquals(NEW_TITLE, product.getTitle());
    }
    @Test
    public void deveDeletarUmProduto(){
        String TITLE = "deletedProduct";
        ProductReqDTO productDTO = getMockedProductDTO();
        productDTO.setTitle(TITLE);
        saveProduct(productDTO);
        ProductResDTO deletedProductDTO = getProductByTitle(TITLE);
        productService.delete(deletedProductDTO.getId());
        assertThrows(NoSuchElementException.class,()->getProductByTitle(TITLE));
    }
    @Test
    public void deveBuscarUmProduto(){
        String TITLE = "Title";
        ProductResDTO productDTO = getProductByTitle(TITLE);
        assertEquals(TITLE, productDTO.getTitle());
    }

    private ProductResDTO saveProduct(ProductReqDTO productDTO){
        return productService.create(productDTO);
    }

    private ProductResDTO getProductByTitle(String title){
        return productService.getByTitle(title, Pageable.unpaged()).getContent().stream().findFirst().get();
    }

    private ProductReqDTO getMockedProductDTO(){
        ProductReqDTO productDTO = new ProductReqDTO();
        productDTO.setTitle("Title");
        productDTO.setDescription("Description");
        productDTO.setValue(new BigDecimal(200));
        return productDTO;
    }
}
