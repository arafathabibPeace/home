package com.iscaghome.controller;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.Billing;
import com.iscaghome.service.BillingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    BillingServices billingServices;

    @GetMapping("/list")
    public ResponseEntity<List<Billing>> getList(){
        List<Billing> result = billingServices.retrieveAll();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Billing> getById(@PathVariable("id")Long id)throws RecordNotFoundException {
        Billing result = billingServices.retrieveById(id);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Billing> createOrUpdate(@Valid @RequestBody Billing billing)throws RecordNotFoundException{
        Billing result = billingServices.createOrUpdate(billing);
        return  new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id")Long id)throws RecordNotFoundException{
        billingServices.deleteById(id);
        return HttpStatus.OK;
    }
}
