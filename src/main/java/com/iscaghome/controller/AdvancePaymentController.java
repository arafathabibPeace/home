package com.iscaghome.controller;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.AdvancePayment;
import com.iscaghome.service.AdvancePaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/advancepayment")
public class AdvancePaymentController {

    @Autowired
    AdvancePaymentServices advancePaymentServices;

    @GetMapping("/list")
    public ResponseEntity<List<AdvancePayment>> getList(){
        List<AdvancePayment> result = advancePaymentServices.retrieveAll();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvancePayment> getById(@PathVariable("id")Long id)throws RecordNotFoundException {
        AdvancePayment result = advancePaymentServices.retrieveById(id);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<AdvancePayment> createOrUpdate(@Valid @RequestBody AdvancePayment advancePayment)throws RecordNotFoundException{
        AdvancePayment result = advancePaymentServices.createOrUpdate(advancePayment);
        return  new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id")Long id)throws RecordNotFoundException{
        advancePaymentServices.deleteById(id);
        return HttpStatus.OK;
    }
}
