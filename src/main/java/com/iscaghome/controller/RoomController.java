package com.iscaghome.controller;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.Room;
import com.iscaghome.service.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomServices roomServices;


    @GetMapping("/list")
    public ResponseEntity<List<Room>> getList(){
        List<Room> result = roomServices.retrieveAll();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/building/{id}")
    public List<Room> getListByBuildingId(@PathVariable(value= "id")Long id){
        return roomServices.retrieveAllByBuildingId(id);
    }

    @GetMapping("/building/{buildingId}/category/{categoryId}")
    public List<Room> getListByBuildingIdAndCategoryId(@PathVariable(value= "buildingId")Long buildingId,@PathVariable(value= "categoryId")Long categoryId){
        return roomServices.retrieveAllByBuildingIdAndCategoryId(buildingId,categoryId);
    }

    @GetMapping("/building/{buildingId}/status/{statusId}")
    public List<Room> getListByBuildingIdAndStatusId(@PathVariable(value= "buildingId")Long buildingId, @PathVariable(value= "statusId")Long statusId){
        return roomServices.retrieveAllByBuildingIdAndStatusId(buildingId,statusId);
    }

    @GetMapping("/{buildingId}/{categoryId}/{statusId}")
    public List<Room> retrieveAllByBuildingIdCategoryIdAndStatusId(@PathVariable(value= "buildingId")Long buildingId, @PathVariable(value= "categoryId")Long categoryId, @PathVariable(value= "statusId")Long statusId){
        return roomServices.retrieveAllByBuildingIdCategoryIdAndStatusId(buildingId, categoryId, statusId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getById(@PathVariable("id")Long id)throws RecordNotFoundException {
        Room result = roomServices.retrieveById(id);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/save/{buildingId}/{categoryId}/{statusId}")
    public ResponseEntity<Room> createOrUpdate(@PathVariable(value= "buildingId")Long buildingId, @PathVariable(value= "categoryId")Long categoryId, @PathVariable(value= "statusId")Long statusId,@Valid @RequestBody Room room)throws RecordNotFoundException{
        Room result = roomServices.createOrUpdate(buildingId,categoryId,statusId,room);
        return  new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }


    @DeleteMapping("/{roomId}/{buildingId}")
    public HttpStatus deleteById(@PathVariable("roomId")Long roomId, @PathVariable("buildingId")Long buildingId)throws RecordNotFoundException{
        roomServices.deleteById(roomId, buildingId);
        return HttpStatus.OK;
    }
}
