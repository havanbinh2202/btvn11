package com.tn.req;

import lombok.Data;

@Data
public class Productreq {
    private String productname;

    private int price;

    private int quantity;
    private String comment;

}
