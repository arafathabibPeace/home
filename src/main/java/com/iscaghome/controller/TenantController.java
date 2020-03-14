package com.iscaghome.controller;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.Tenant;
import com.iscaghome.service.TenantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/tenant")
public class TenantController {

    @Autowired
    TenantServices tenantServices;

    @GetMapping("/list")
    public ResponseEntity<List<Tenant>> getList(){
        List<Tenant> result = tenantServices.retrieveAll();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tenant> getById(@PathVariable("id")Long id)throws RecordNotFoundException {
        Tenant result = tenantServices.retrieveById(id);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Tenant> createOrUpdate(@Valid @RequestBody Tenant tenant)throws RecordNotFoundException{
        Tenant result = tenantServices.createOrUpdate(tenant);
        return  new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id")Long id)throws RecordNotFoundException{
        tenantServices.deleteById(id);
        return HttpStatus.OK;
    }
}
