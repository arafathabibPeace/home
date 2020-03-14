package com.iscaghome.controller;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.RoomStatus;
import com.iscaghome.service.RoomStatusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/roomstatus")
public class RoomStatusController {

    @Autowired
    RoomStatusServices roomStatusServices;

    @GetMapping("/list")
    public ResponseEntity<List<RoomStatus>> getList(){
        List<RoomStatus> result = roomStatusServices.retrieveAll();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomStatus> getById(@PathVariable("id")Long id)throws RecordNotFoundException{
        RoomStatus result = roomStatusServices.retrieveById(id);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<RoomStatus> createOrUpdate(@Valid @RequestBody RoomStatus roomStatus)throws RecordNotFoundException{
        RoomStatus result = roomStatusServices.createOrUpdate(roomStatus);
        return new ResponseEntity<>(result, new HttpHeaders(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id")Long id)throws RecordNotFoundException{
        roomStatusServices.deleteById(id);
        return HttpStatus.OK;
    }
}
