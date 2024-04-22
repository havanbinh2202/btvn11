package com.tn.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String categoryname;

    private Date createdAt;

    private Date updatedAt;
}
