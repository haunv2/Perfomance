package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sq", allocationSize = 10000)
    @GeneratedValue(generator = "user_generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FULLNAME")
    private String fullname;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "ADDRESS")
    private String address;
}