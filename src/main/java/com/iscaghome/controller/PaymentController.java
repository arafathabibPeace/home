package com.iscaghome.controller;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.Payment;
import com.iscaghome.service.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentServices paymentServices;

    @GetMapping("/list")
    public ResponseEntity<List<Payment>> getList(){
        List<Payment> result = paymentServices.retrieveAll();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getById(@PathVariable("id")Long id)throws RecordNotFoundException {
        Payment result = paymentServices.retrieveById(id);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Payment> createOrUpdate(@Valid @RequestBody Payment payment)throws RecordNotFoundException{
        Payment result = paymentServices.createOrUpdate(payment);
        return  new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id")Long id)throws RecordNotFoundException{
        paymentServices.deleteById(id);
        return HttpStatus.OK;
    }
}
