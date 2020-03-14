package com.iscaghome.service;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.RoomPicture;
import com.iscaghome.repository.RoomPictureRepository;
import com.iscaghome.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomPictureServices {
    @Autowired
    RoomPictureRepository roomPictureRepository;

    @Autowired
    RoomRepository roomRepository;

    public List<RoomPicture> retrieveAll(){
        List<RoomPicture> result = roomPictureRepository.findAll();
        if(result.size()>0){
            return result;
        }else{
            return new ArrayList<RoomPicture>();
        }
    }
    public List<RoomPicture> retrieveAllByRoomId(Long id){
        if(roomRepository.existsById(id)){
            return roomPictureRepository.findByRoomId(id);
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
    public RoomPicture retrieveById(Long id) throws RecordNotFoundException {
        Optional<RoomPicture> result = roomPictureRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
    public RoomPicture createOrUpdate(Long pictureId, Long roomId, String fileName, byte[] fileData) throws RecordNotFoundException, IOException {
        RoomPicture roomPicture = new RoomPicture();
        if(roomRepository.existsById(roomId)){
            if(roomPictureRepository.existsById(pictureId)){
                Optional<RoomPicture> result = roomPictureRepository.findById(pictureId);
                if(result.isPresent()){
                    RoomPicture newRoomPicture = result.get();
                    newRoomPicture.setName(fileName);
                    newRoomPicture.setImageData(fileData);
                    newRoomPicture.setRoom(roomRepository.findById(roomId).get());
                    return newRoomPicture;
                }else{
                    roomPicture.setName(fileName);
                    roomPicture.setImageData(fileData);
                    roomPicture.setRoom(roomRepository.findById(roomId).get());
                    roomPictureRepository.save(roomPicture);
                    return roomPicture;
                }
            }else{
                roomPicture.setName(fileName);
                roomPicture.setImageData(fileData);
                roomPicture.setRoom(roomRepository.findById(roomId).get());
                roomPictureRepository.save(roomPicture);
                return roomPicture;
            }
        }else{
            throw new RecordNotFoundException("Id does not exist", roomId);
        }

    }
    public void deleteById(Long id)throws RecordNotFoundException{
        Optional<RoomPicture> result = roomPictureRepository.findById(id);
        if(result.isPresent()){
            roomPictureRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
}
