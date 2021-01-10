package br.com.challenge.shoppingcart.service;

import br.com.challenge.shoppingcart.domain.Product;
import br.com.challenge.shoppingcart.dto.product.ProductReqDTO;
import br.com.challenge.shoppingcart.dto.product.ProductResDTO;
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
    public ProductResDTO create(ProductReqDTO productDTO){
        Product product = modelMapper.map(productDTO,Product.class);
        product.setId(null);
        return modelMapper.map(save(product), ProductResDTO.class);
    }

    @Transactional
    public ProductResDTO update(Long id, ProductReqDTO productDTO) {
        Product product = findByIdAndDeletedFalse(id);
        modelMapper.map(productDTO, product);
        product.setId(id);
        return modelMapper.map(save(product), ProductResDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        Product product = findByIdAndDeletedFalse(id);
        product.setDeleted(true);
        save(product);
    }

    public Page<ProductResDTO> getAll(Pageable pageable) {
        return productRepository.findAllByDeletedFalse(pageable).map(p -> modelMapper.map(p, ProductResDTO.class));
    }

    public Page<ProductResDTO> getByTitle(String title, Pageable pageable) {
        return productRepository.findAllByTitleIsContainingAndDeletedFalse(title, pageable).map(p->modelMapper.map(p, ProductResDTO.class));
    }

    public Product getById(Long productId) {
        return findByIdAndDeletedFalse(productId);
    }

    private Product save(Product product){
        return productRepository.save(product);
    }

    private Product findByIdAndDeletedFalse(Long id){
        return productRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(()->new IllegalArgumentException("error.product.not.found"));
    }
}
