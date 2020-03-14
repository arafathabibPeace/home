package com.iscaghome.controller;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.Building;
import com.iscaghome.service.BuildingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    BuildingServices buildingServices;

    @GetMapping("/list")
    public ResponseEntity<List<Building>> getList(){
        List<Building> result = buildingServices.retrieveAll();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Building> getById(@PathVariable("id")Long id)throws RecordNotFoundException {
        Building result = buildingServices.retrieveById(id);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Building> createOrUpdate(@Valid @RequestBody Building building)throws RecordNotFoundException{
        Building result = buildingServices.createOrUpdate(building);
        return  new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id")Long id)throws RecordNotFoundException{
        buildingServices.deleteById(id);
        return HttpStatus.OK;
    }
}
