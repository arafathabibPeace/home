package com.iscaghome.controller;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.PaymentType;
import com.iscaghome.service.PaymentTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/paymenttype")
public class PaymentTypeController {

    @Autowired
    PaymentTypeServices paymentTypeServices;

    @GetMapping("/list")
    public ResponseEntity<List<PaymentType>> getList(){
        List<PaymentType> result = paymentTypeServices.retrieveAll();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentType> getById(@PathVariable("id")Long id)throws RecordNotFoundException {
        PaymentType result = paymentTypeServices.retrieveById(id);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<PaymentType> createOrUpdate(@Valid @RequestBody PaymentType payment_type)throws RecordNotFoundException{
        PaymentType result = paymentTypeServices.createOrUpdate(payment_type);
        return  new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id")Long id)throws RecordNotFoundException{
        paymentTypeServices.deleteById(id);
        return HttpStatus.OK;
    }
}
