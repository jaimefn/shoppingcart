package br.com.challenge.shoppingcart.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {
    @Autowired
    ModelMapper modelMapper;
}
