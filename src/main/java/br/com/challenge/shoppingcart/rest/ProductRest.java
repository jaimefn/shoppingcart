package br.com.challenge.shoppingcart.rest;

import br.com.challenge.shoppingcart.dto.product.ProductDTO;
import br.com.challenge.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class ProductRest {


    private ProductService productService;

    @Autowired
    public ProductRest(ProductService productService){
        this.productService = productService;
    }

    @RequestMapping(value = "/products",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.create(productDTO), HttpStatus.CREATED);
    }
    @RequestMapping(value = "/products/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long id){
        return new ResponseEntity<>(productService.update(id, productDTO), HttpStatus.OK);
    }
    @RequestMapping(value = "/products/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/products",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProduct(Pageable pageable){
        return new ResponseEntity<>(productService.getAll(pageable), HttpStatus.OK);
    }
    @RequestMapping(value = "/products/{title}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdProduct(@PathVariable String title, Pageable pageable){
        return new ResponseEntity<>(productService.getByTitle(title, pageable), HttpStatus.OK);
    }
}
