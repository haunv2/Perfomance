package com.controller;

import com.model.Category;
import com.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository repo;

    @GetMapping("/cat")
    public Category add(@RequestParam("name") String name) {
        return repo.save(new Category(null, name));
    }
}
