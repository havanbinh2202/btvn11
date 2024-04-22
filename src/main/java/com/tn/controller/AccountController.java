package com.tn.controller;

import com.tn.entity.Account;
import com.tn.repository.AccountRepository;
import com.tn.req.Accountreq;
import com.tn.req.Accountrequpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class AccountController {
    @Autowired
    private AccountRepository accountRepo;

    @GetMapping("account")
    public ResponseEntity<?> getAll() {
        List<Account> accounts = accountRepo.findAll();
        return new ResponseEntity<>("Get all account: " + accounts, HttpStatus.OK);

    }

    @GetMapping("account/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {

        Account account = accountRepo.findById(id).orElse(null);
        if (account == null) {
            return new ResponseEntity<>("Account not found ", HttpStatus.BAD_REQUEST);
        }
        accountRepo.findById(id);

        return new ResponseEntity<>(account.getUsername(), HttpStatus.OK);

    }
    @PostMapping("save")
    public  ResponseEntity<?> save(@RequestBody Accountreq accountreq) {
        log.info("Save new account ");

        Account accountDb = accountRepo.findByUsername(accountreq.getUsername());
        Account accountDb1 = accountRepo.findByEmail(accountreq.getUsername());
        if(accountDb != null || accountDb1 != null ){
            return new ResponseEntity<>("Account name or email already exitting" + accountreq.getUsername() + accountreq.getEmail(), HttpStatus.OK);
        }

        Account account = new Account();

        account.setFullname(accountreq.getFullname());
        account.setUsername(accountreq.getUsername());
        account.setPassword(accountreq.getPassword());
        account.setEmail(accountreq.getEmail());

        accountRepo.save(account);
        log.info("Đã thêm 1 account mới! ",account.getUsername());

        return new ResponseEntity<>("Create sucesslly " + account, HttpStatus.OK);
    }

    @PutMapping("account/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody Accountrequpdate accountrequpdate) {

        Account account = accountRepo.findById(id).orElse(null);
        System.out.println(account);
        if (account != null){
            log.warn("Not found with id " + id);
            account.setFullname(accountrequpdate.getFullname());
            account.setUsername(accountrequpdate.getUsername());
            account.setPassword(accountrequpdate.getPassword());
            account.setEmail(accountrequpdate.getEmail());
            accountRepo.save(account);
            return new ResponseEntity<>("Update suscesslly:", HttpStatus.OK);
        }
        log.info("Đã cập nhật thông tin của account: ", account.getUsername());

        return new ResponseEntity<>("Fail not Account with id "+ id, HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("account/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){

        Account account = accountRepo.findById(id).orElse(null);
        if (account == null){
            return new ResponseEntity<>("Account id  exiting",HttpStatus.BAD_REQUEST);
        }
        accountRepo.deleteById(id);

        log.info("Đã xóa account có ID: ", id);

        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }

}