package com.tn.controller;

import com.tn.entity.Account;
import com.tn.entity.Product;
import com.tn.repository.ProductRepository;
import com.tn.req.Accountrequpdate;
import com.tn.req.Productreq;
import com.tn.req.Productrequpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ProductController {
    @Autowired

    private ProductRepository productRepo;

    @GetMapping("product")
    public ResponseEntity<?> getAll(){
        List<Product> products = productRepo.findAll();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("saveproduct")
    public  ResponseEntity<?> save(@RequestBody Productreq productreq) {

        Product productDb = productRepo.findByProductname(productreq.getProductname());
        if(productDb != null){
            return new ResponseEntity<>("Product name exitting" + productreq.getProductname(), HttpStatus.OK);
        }

        Product product = new Product();

        product.setProductname(productreq.getProductname());
        product.setPrice(product.getPrice());
        product.setQuantity(product.getQuantity());
        product.setComment(product.getComment());

        productRepo.save(product);
        log.info("Đã thêm 1 account mới! ",product.getProductname());

        return new ResponseEntity<>("Create sucesslly " + product, HttpStatus.OK);
    }
    @PutMapping("product/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody Productrequpdate productrequpdate) {

        Product product = productRepo.findById(id).orElse(null);
        System.out.println(product);
        if (product != null){
            log.warn("Not found with id " + id);
            product.setProductname(productrequpdate.getProductname());
            product.setPrice(productrequpdate.getPrice());
            product.setQuantity(productrequpdate.getQuantity());
            product.setComment(productrequpdate.getComment());

            productRepo.save(product);
            return new ResponseEntity<>("Update suscesslly:", HttpStatus.OK);
        }
        log.info("Đã cập nhật thông tin của product: ", product.getProductname());

        return new ResponseEntity<>("Fail not product with id "+ id, HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("product/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){

        Product product = productRepo.findById(id).orElse(null);
        if (product == null){
            return new ResponseEntity<>("Account id  exiting",HttpStatus.BAD_REQUEST);
        }
        productRepo.deleteById(id);

        log.info("Đã xóa product có ID: ", id);

        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }


}
