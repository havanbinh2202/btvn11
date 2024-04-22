package com.tn.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String productname;

    private int price;

    private int quantity;

    private String comment;

    private Date createdAt;
    
    private Date updatedAt;
}
