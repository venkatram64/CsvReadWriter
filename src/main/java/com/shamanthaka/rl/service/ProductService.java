package com.shamanthaka.rl.service;

import com.shamanthaka.rl.model.Product;
import com.shamanthaka.rl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /*@Autowired//constructor injection
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }*/

    public Product save(Product prod){
        Product p = productRepository.saveAndFlush(prod);
        return p;
    }

    public List<Product> findAll(){
        List<Product> prods = productRepository.findAll();
        return prods;
    }
}
