package br.com.challenge.shoppingcart.config;

import br.com.challenge.shoppingcart.domain.Product;
import br.com.challenge.shoppingcart.dto.product.ProductResDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(skipModifiedFieldsMap);
        return modelMapper;
    }
    PropertyMap<ProductResDTO, Product> skipModifiedFieldsMap = new PropertyMap<ProductResDTO, Product>() {
        protected void configure() {
            skip().setCreatedDate(null);
        }
    };
}
