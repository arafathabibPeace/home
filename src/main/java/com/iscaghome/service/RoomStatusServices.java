package com.iscaghome.service;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.RoomStatus;
import com.iscaghome.repository.RoomStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomStatusServices {

    @Autowired
    RoomStatusRepository roomStatusRepository;

    public List<RoomStatus> retrieveAll(){
        List<RoomStatus> result = roomStatusRepository.findAll();
        if(result.size()>0){
            return result;
        }else{
            return new ArrayList<RoomStatus>();
        }
    }
    public RoomStatus retrieveById(Long id)throws RecordNotFoundException {
        Optional<RoomStatus> result = roomStatusRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
    public RoomStatus createOrUpdate(RoomStatus roomStatus)throws RecordNotFoundException {
        if(roomStatus.getId()!=null){
            Optional<RoomStatus> result = roomStatusRepository.findById(roomStatus.getId());
            if(result.isPresent()){
                RoomStatus newRoomStatus = result.get();
                newRoomStatus.setLabel(roomStatus.getLabel());
                newRoomStatus.setDescription(roomStatus.getDescription());

                newRoomStatus = roomStatusRepository.save(newRoomStatus);

                return newRoomStatus;
            }else{
                roomStatus = roomStatusRepository.save(roomStatus);
                return roomStatus;
            }

        }else{
            roomStatus = roomStatusRepository.save(roomStatus);
            return roomStatus;
        }
    }
    public void deleteById(Long id)throws RecordNotFoundException{
        Optional<RoomStatus> result = roomStatusRepository.findById(id);
        if(result.isPresent()){
            roomStatusRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
}
