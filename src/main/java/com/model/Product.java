package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(name = "product_generator", sequenceName = "product_sq", allocationSize = 100000)
    @GeneratedValue(generator = "product_sq")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private Long price;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "USER_ID")
    private Long userCreate;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "product_categories",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID")})
    private Set<Category> categories;

}