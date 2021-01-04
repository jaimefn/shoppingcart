package br.com.challenge.shoppingcart.service;

import br.com.challenge.shoppingcart.domain.Product;
import br.com.challenge.shoppingcart.dto.product.ProductDTO;
import br.com.challenge.shoppingcart.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.math.BigDecimal;
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
        ProductDTO productDTO = getMockedProductDTO();
        saveProduct(productDTO);
    }

    @Test
    public void deveSalvarUmProduto(){
        ProductDTO productDTO = getMockedProductDTO();
        productDTO.setTitle("newProduct");
        ProductDTO newProduct = saveProduct(productDTO);
        assertTrue(newProduct != null);
    }
    @Test
    public void deveAtualizarUmProduto(){
        final String OLD_TITLE = "Title";
        final String NEW_TITLE = "Title2";
        ProductDTO productDTO = getProductByTitle(OLD_TITLE);
        productDTO.setTitle(NEW_TITLE);
        ProductDTO product = productService.update(productDTO.getId(), productDTO);
        assertEquals(NEW_TITLE, product.getTitle());
    }
    @Test
    public void deveDeletarUmProduto(){
        String TITLE = "deletedProduct";
        ProductDTO productDTO = getMockedProductDTO();
        productDTO.setTitle(TITLE);
        saveProduct(productDTO);
        productDTO = getProductByTitle(TITLE);
        productService.delete(productDTO.getId());
        productDTO = getProductByTitle(TITLE);
        assertNull(productDTO);
    }
    @Test
    public void deveBuscarUmProduto(){
        String TITLE = "Title";
        ProductDTO productDTO = getProductByTitle(TITLE);
        assertEquals(TITLE, productDTO.getTitle());
    }

    private ProductDTO saveProduct(ProductDTO productDTO){
        return productService.create(productDTO);
    }

    private ProductDTO getProductByTitle(String title){
        Optional<ProductDTO> product = productService.getByTitle(title, Pageable.unpaged()).getContent().stream().findFirst();
        if(product.isPresent()){
            return product.get();
        }
        return null;
    }

    private ProductDTO getMockedProductDTO(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setTitle("Title");
        productDTO.setDescription("Description");
        productDTO.setValue(new BigDecimal(200));
        productDTO.setStock(10);
        return productDTO;
    }
}
