package br.com.challenge.shoppingcart.service;

import br.com.challenge.shoppingcart.domain.Product;
import br.com.challenge.shoppingcart.dto.ProductDTO;
import br.com.challenge.shoppingcart.exceptions.UserNotFoundException;
import br.com.challenge.shoppingcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService extends BaseService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductDTO create(ProductDTO productDTO){
        Product product = modelMapper.map(productDTO,Product.class);
        product.setId(null);
        return modelMapper.map(save(product),ProductDTO.class);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product product = findById(id);
        modelMapper.map(productDTO, product);
        product.setId(id);
        return modelMapper.map(save(product),ProductDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        Product product = findById(id);
        product.setDeleted(true);
        save(product);
    }

    public Page<ProductDTO> getAll(Pageable pageable) {
        return productRepository.findAllByDeletedFalse(pageable).map(p -> modelMapper.map(p,ProductDTO.class));
    }

    public Page<ProductDTO> getByTitle(String title, Pageable pageable) {
        return productRepository.findAllByTitleIsContainingAndDeletedFalse(title, pageable).map(p->modelMapper.map(p,ProductDTO.class));
    }

    private Product save(Product product){
        return productRepository.save(product);
    }

    private Product findById(Long id){
        return productRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
