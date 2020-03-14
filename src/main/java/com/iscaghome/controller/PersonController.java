package com.iscaghome.controller;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.Person;
import com.iscaghome.service.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonServices personServices;

    @GetMapping("/list")
    public ResponseEntity<List<Person>> getList(){
        List<Person> result = personServices.retrieveAll();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id")Long id)throws RecordNotFoundException
    {
        Person result = personServices.retrieveById(id);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }


    @PostMapping(value="/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Person> createOrUpdate(
            @RequestParam("id")Long id,
            @RequestParam("firstName")String firstName,
            @RequestParam("middleName")String middleName,
            @RequestParam("lastName")String lastName,
            @RequestParam("gender")String gender,
            @RequestParam("contactNumber")String contactNumber,
            @RequestParam("presentAddress")String presentAddress,
            @RequestParam("homeAddress")String homeAddress,
            @RequestParam("email")String email,
            @RequestParam("file") MultipartFile file) throws RecordNotFoundException, IOException {

        Person result =  new Person(
                id,
                firstName,
                middleName,
                lastName,
                gender,
                contactNumber,
                presentAddress,
                homeAddress,
                email,
                file.getBytes());

                personServices.createOrUpdate(result);

        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id")Long id)throws RecordNotFoundException{
        personServices.deleteById(id);
        return HttpStatus.OK;
    }

}
