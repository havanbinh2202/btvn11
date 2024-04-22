package com.tn.controller;

import com.tn.entity.Account;
import com.tn.entity.Category;
import com.tn.repository.CategoryRepository;
import com.tn.req.Accountreq;
import com.tn.req.Accountrequpdate;
import com.tn.req.Categoryreq;
import com.tn.req.Categoryrequpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CategoryController {
    @Autowired

    private CategoryRepository categoryRepo;

    @GetMapping("category")
    public ResponseEntity<?> getAll() {
        List<Category> category = categoryRepo.findAll();
        return new ResponseEntity<>("Get all account: " + category, HttpStatus.OK);

    }

    @PostMapping("savecategory")
    public  ResponseEntity<?> save(@RequestBody Categoryreq categoryreq) {
        log.info("Save new category ");

        Category categoryDb = categoryRepo.findByCategoryname(categoryreq.getCategoryname());
        if(categoryDb != null ){
            return new ResponseEntity<>("Category name  already exitting" + categoryreq.getCategoryname(), HttpStatus.OK);
        }

        Category category = new Category();

        category.setCategoryname(categoryreq.getCategoryname());

        categoryRepo.save(category);
        log.info("Đã thêm 1 account mới! ",category.getCategoryname());

        return new ResponseEntity<>("Create sucesslly " + category, HttpStatus.OK);
    }

    @PutMapping("category/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody Categoryrequpdate categoryrequpdate) {

        Category category = categoryRepo.findById(id).orElse(null);
        System.out.println(category);
        if (category != null){
            log.warn("Not found with id " + id);
            category.setCategoryname(categoryrequpdate.getCategoryname());
            categoryRepo.save(category);
            return new ResponseEntity<>("Update suscesslly:", HttpStatus.OK);
        }
        log.info("Đã cập nhật thông tin của Category: ", category.getCategoryname());

        return new ResponseEntity<>("Fail not Account with id "+ id, HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("category/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){

        Category category = categoryRepo.findById(id).orElse(null);
        if (category == null){
            return new ResponseEntity<>("Category id  exiting",HttpStatus.BAD_REQUEST);
        }
        categoryRepo.deleteById(id);

        log.info("Đã xóa category có ID: ", id);

        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }
}
