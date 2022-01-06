package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @SequenceGenerator(name = "cat_generator", sequenceName = "cat_sq", allocationSize = 100000)
    @GeneratedValue(generator = "cat_sq")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 50)
    private String name;
}