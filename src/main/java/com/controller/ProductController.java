package com.controller;

import com.Service.ProductService;
import com.model.Category;
import com.model.Product;
import com.model.User;
import com.repository.CategoryRepository;
import com.repository.ProductRepository;
import com.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import java.util.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    static Map<Long, User> us = new HashMap<>();
    static Map<Long, Category> cats = new HashMap<>();

    private final ProductService service;

    private final CategoryRepository catRepo;

    Logger log = LoggerFactory.getLogger(ProductController.class);

    String[] alphabets = {"A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};

    Map<Integer, Category> categories = new HashMap<>();

    private TaskExecutor taskExecutor;

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public ProductController(ProductService service, CategoryRepository catRepo, EntityManagerFactory entityManagerFactory, TaskExecutor taskExecutor) {
        this.service = service;
        this.catRepo = catRepo;
        this.taskExecutor = taskExecutor;
        this.entityManagerFactory = entityManagerFactory;
        catRepo.findAll().forEach(c -> {
            categories.put(c.getId().intValue(), c);
        });
    }

    @GetMapping("/add")
    public Object add() {
        log.info("Time Start-> " + System.currentTimeMillis());
        for (int i = 0; i < 50; ++i)
            doTask();
        return System.currentTimeMillis();
    }

    String getString(int length) {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            s.append(alphabets[(int) (Math.random() * 25) + 0]);
            if (i % 10 == 0)
                s.append(" ");
        }
        return s.toString();
    }


    public void doTask() {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

        taskExecutor.execute(() -> {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin(); // begin transaction

            //begin insert
            for (int j = 1; j < 1000001; ++j) {
                Long user = Long.valueOf(((int) (Math.random() * 100) + 1)); // get random user from 1 - 100
                Set<Category> cats = new HashSet<>();
                int catCount = ((int) (Math.random() * 17) + 1); // random total cat per product

                //add cat
                for (int i = 0; i < catCount; i++) {
                    cats.add(categories.get((int) (Math.random() * 18) + 4));
                }
                //save product
                service.addProduct(
                        new Product(null, getString(124), getString(235), 129312491l, getString(200), getString(30), user, cats));
                if (j % 100000 == 0) {
                    taskExecutor.execute(() -> {
                        session.flush();
                        session.clear();
                    });
                }
            }
            session.getTransaction().commit(); // commit tran
            session.close(); // close
            log.info("end insert 100.000 records");
            log.info("Time end->" + System.currentTimeMillis());
        });
    }
}
