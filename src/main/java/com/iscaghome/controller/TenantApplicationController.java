package com.iscaghome.controller;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.TenantApplication;
import com.iscaghome.service.TenantApplicationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/application")
public class TenantApplicationController {

    @Autowired
    TenantApplicationServices tenantApplicationServices;

    @GetMapping("/list")
    public ResponseEntity<List<TenantApplication>> getList(){
        List<TenantApplication> result = tenantApplicationServices.retrieveAll();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantApplication> getById(@PathVariable("id")Long id)throws RecordNotFoundException {
        TenantApplication result = tenantApplicationServices.retrieveById(id);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<TenantApplication> createOrUpdate(@Valid @RequestBody TenantApplication tenantApplication)throws RecordNotFoundException{
        TenantApplication result = tenantApplicationServices.createOrUpdate(tenantApplication);
        return  new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id")Long id)throws RecordNotFoundException{
        tenantApplicationServices.deleteById(id);
        return HttpStatus.OK;
    }
}
