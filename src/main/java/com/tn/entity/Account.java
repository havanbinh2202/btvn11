package com.tn.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String fullname;

    private String username;

    private String password;

    private String email;


    private Date createdAt;

    private Date updatedAt;

    public void setUsername(String username) {
        if (username.length() <= 20) {
            this.username = username;
        } else {
            // Xử lý khi username vượt quá 20 kí tự
            System.out.println("Username không được vượt quá 20 kí tự");
        }
    }

}
