package com.Service;

import com.model.Product;
import com.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    @Value("${maxResultFetch}")
    private Integer maxFetchRecords;

    public Product getProduct(Long id) {
        return repo.getById(id);
    }

    public Product addProduct(Product obj) {
        return repo.save(obj);
    }

    public Product updateProduct(Product obj) {
        return repo.saveAndFlush(obj);
    }

    public Boolean deleteProduct(Long id) {
        repo.deleteById(id);
        return true;
    }

    public List<Product> getAll(Specification specs, Integer page) {
        return repo.findAll(specs, PageRequest.of(page, maxFetchRecords)).toList();
    }

    public Long count(Specification specs) {
        return repo.count(specs);
    }
}
