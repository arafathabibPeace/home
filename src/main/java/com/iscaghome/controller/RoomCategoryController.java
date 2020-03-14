package com.iscaghome.controller;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.RoomCategory;
import com.iscaghome.service.RoomCategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/roomcategory")
public class RoomCategoryController {

    @Autowired
    RoomCategoryServices roomCategoryServices;

    @GetMapping("/list")
    public ResponseEntity<List<RoomCategory>> getList(){
        List<RoomCategory> result = roomCategoryServices.retrieveAll();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomCategory> getById(@PathVariable("id")Long id)throws RecordNotFoundException {
        RoomCategory result = roomCategoryServices.retrieveById(id);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<RoomCategory> createOrUpdate(@Valid @RequestBody RoomCategory room_category)throws RecordNotFoundException{
        RoomCategory result = roomCategoryServices.createOrUpdate(room_category);
        return  new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id")Long id)throws RecordNotFoundException{
        roomCategoryServices.deleteById(id);
        return HttpStatus.OK;
    }


}
