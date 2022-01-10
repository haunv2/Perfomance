package com.controller;

import com.Service.ProductService;
import com.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PController {
    @Autowired
    private ProductService service;


    @GetMapping("/id")
    public Product getProduct(@PathVariable("id") Long id) {
        return service.getProduct(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product obj) {
        return service.addProduct(obj);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product obj) {
        obj.setId(id);
        return service.updateProduct(obj);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteProduct(@PathVariable("id") Long id) {
        return service.deleteProduct(id);
    }

    @GetMapping("/")
    public Object getAll(@RequestParam(name = "page", value = "0") Integer page) {
        return service.getAll(null, page);
    }
}
