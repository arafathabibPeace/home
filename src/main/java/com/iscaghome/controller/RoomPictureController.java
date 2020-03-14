package com.iscaghome.controller;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.RoomPicture;
import com.iscaghome.service.RoomPictureServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/picture")
public class RoomPictureController {

    @Autowired
    RoomPictureServices roomPictureServices;

    @GetMapping("/list")
    public ResponseEntity<List<RoomPicture>> getList(){
        List<RoomPicture> result = roomPictureServices.retrieveAll();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/room/{id}")
    public List<RoomPicture> getAllByRoomId(@RequestParam(value = "id")Long id){
        return roomPictureServices.retrieveAllByRoomId(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RoomPicture> getById(@RequestParam("id")Long id)throws RecordNotFoundException {
        RoomPicture result = roomPictureServices.retrieveById(id);

        return new ResponseEntity<>(result,new HttpHeaders(), HttpStatus.OK);

    }

    @PostMapping(value="/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RoomPicture> createOrUpdate(@RequestParam("id")Long id, @RequestParam("roomId")Long roomId, @RequestParam("files") MultipartFile[] files) throws IOException {
        RoomPicture result= new RoomPicture();
        for(MultipartFile file:files){
            result = roomPictureServices.createOrUpdate(id, roomId, file.getOriginalFilename(),file.getBytes());
        }
        return new ResponseEntity<>(result,new HttpHeaders(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id")Long id)throws RecordNotFoundException{
        roomPictureServices.deleteById(id);
        return HttpStatus.OK;
    }
}
