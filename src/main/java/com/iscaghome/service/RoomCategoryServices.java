package com.iscaghome.service;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.RoomCategory;
import com.iscaghome.repository.RoomCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomCategoryServices {

    @Autowired
    RoomCategoryRepository roomCategoryRepository;

    public List<RoomCategory> retrieveAll(){
        List<RoomCategory> result = roomCategoryRepository.findAll();
        if(result.size()>0){
            return result;
        }else{
            return new ArrayList<RoomCategory>();
        }
    }

    public RoomCategory retrieveById(Long id) throws RecordNotFoundException{
        Optional<RoomCategory> result = roomCategoryRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
    public RoomCategory createOrUpdate(RoomCategory roomCategory)throws RecordNotFoundException{

        if(roomCategory.getId()!=null){
            Optional<RoomCategory> result = roomCategoryRepository.findById(roomCategory.getId());
            if(!result.isPresent()){
                RoomCategory newRoomCategory = result.get();
                newRoomCategory.setLabel(roomCategory.getLabel());
                newRoomCategory.setRate(roomCategory.getRate());
                newRoomCategory.setRateUnit(roomCategory.getRateUnit());
                newRoomCategory.setPermittedOccupantsNumber(roomCategory.getPermittedOccupantsNumber());
                newRoomCategory.setDescription(roomCategory.getDescription());
                return newRoomCategory;
            }else{
                roomCategory = roomCategoryRepository.save(roomCategory);
                return roomCategory;
            }
        }else{
            roomCategory = roomCategoryRepository.save(roomCategory);
            return roomCategory;
        }
    }
    public void deleteById(Long id)throws RecordNotFoundException{
        Optional<RoomCategory> result = roomCategoryRepository.findById(id);
        if(result.isPresent()){
            roomCategoryRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
}
