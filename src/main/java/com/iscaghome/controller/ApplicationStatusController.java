package com.iscaghome.controller;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.ApplicationStatus;
import com.iscaghome.service.ApplicationStatusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/applicationstatus")
public class ApplicationStatusController {

    @Autowired
    ApplicationStatusServices applicationStatusServices;

    @GetMapping("/list")
    public ResponseEntity<List<ApplicationStatus>> getList(){
        List<ApplicationStatus> result = applicationStatusServices.retrieveAll();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationStatus> getById(@PathVariable("id")Long id)throws RecordNotFoundException {
        ApplicationStatus result = applicationStatusServices.retrieveById(id);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ApplicationStatus> createOrUpdate(@Valid @RequestBody ApplicationStatus applicationStatus)throws RecordNotFoundException{
        ApplicationStatus result = applicationStatusServices.createOrUpdate(applicationStatus);
        return  new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id")Long id)throws RecordNotFoundException{
        applicationStatusServices.deleteById(id);
        return HttpStatus.OK;
    }
}
