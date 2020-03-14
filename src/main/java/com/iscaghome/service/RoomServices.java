package com.iscaghome.service;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.Building;
import com.iscaghome.model.Room;
import com.iscaghome.model.RoomCategory;
import com.iscaghome.model.RoomStatus;
import com.iscaghome.repository.BuildingRepository;
import com.iscaghome.repository.RoomCategoryRepository;
import com.iscaghome.repository.RoomRepository;
import com.iscaghome.repository.RoomStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomServices {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    RoomCategoryRepository roomCategoryRepository;

    @Autowired
    RoomStatusRepository roomStatusRepository;

    public List<Room> retrieveAll() {
        List<Room> result = roomRepository.findAll();
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Room>();
        }
    }

    public List<Room> retrieveAllByBuildingId(Long id){
        Optional<Building> result = buildingRepository.findById(id);
       if(result.isPresent()){
           return roomRepository.findByBuildingId(id);
       }else{
           throw new RecordNotFoundException("Id does not exist", id);
       }
    }

    public List<Room> retrieveAllByBuildingIdAndCategoryId(Long buildingId, Long categoryId){
        Optional<Building> buildingResult = buildingRepository.findById(buildingId);
        if(buildingResult.isPresent()){
            Optional<RoomCategory> categoryResult = roomCategoryRepository.findById(categoryId);
            if(categoryResult.isPresent()){
                return roomRepository.findByBuildingIdAndCategoryId(buildingId,categoryId);
            }else{
                throw new RecordNotFoundException("Category Id does not exist", categoryId);
            }
        }else {
            throw new RecordNotFoundException("Building Id does not exist", buildingId);
        }
    }
    public List<Room> retrieveAllByBuildingIdAndStatusId(Long buildingId, Long statusId){
        Optional<Building> buildingResult = buildingRepository.findById(buildingId);
        if(buildingResult.isPresent()){
            Optional<RoomStatus> statusResult = roomStatusRepository.findById(statusId);
            if(statusResult.isPresent()){
                return roomRepository.findByBuildingIdAndStatusId(buildingId,statusId);
            }else{
                throw new RecordNotFoundException("Status Id does not exist", statusId);
            }
        }else {
            throw new RecordNotFoundException("Building Id does not exist", buildingId);
        }
    }

    public List<Room> retrieveAllByBuildingIdCategoryIdAndStatusId(Long buildingId, Long categoryId, Long statusId){
        Optional<Building> buildingResult = buildingRepository.findById(buildingId);
        if(buildingResult.isPresent()){
            Optional<RoomCategory> categoryResult = roomCategoryRepository.findById(categoryId);
            if(categoryResult.isPresent()){
                Optional<RoomStatus> statusResult = roomStatusRepository.findById(statusId);
                if(statusResult.isPresent()){
                    return roomRepository.findByBuildingIdAndCategoryIdAndStatusId(buildingId,categoryId,statusId);
                }else{
                    throw new RecordNotFoundException("Status Id does not exist", statusId);
                }
            }else{
                throw new RecordNotFoundException("Category Id does not exist", categoryId);
            }
        }else {
            throw new RecordNotFoundException("Building Id does not exist", buildingId);
        }
    }

    public Room retrieveById(Long id) throws RecordNotFoundException {
        Optional<Room> result = roomRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }

    public Room createOrUpdate(Long buildingId,Long categoryId, Long statusId, Room room) throws RecordNotFoundException{

        if(room.getId()!=null){
            Optional<Building> buildingResult = buildingRepository.findById(buildingId);
            if(buildingResult.isPresent()){
                Optional<RoomCategory> categoryResult = roomCategoryRepository.findById(categoryId);
                if(categoryResult.isPresent()){
                    Optional<RoomStatus> statusResult = roomStatusRepository.findById(statusId);
                    if(statusResult.isPresent()){
                        Optional<Room> roomResult = roomRepository.findById(room.getId());
                        if(!roomResult.isPresent()){
                            Room newRoom = roomResult.get();
                            newRoom.setBuilding(buildingResult.get());
                            newRoom.setCategory(categoryResult.get());
                            newRoom.setStatus(statusResult.get());
                            roomRepository.save(newRoom);
                            return newRoom;
                        }else{
                            room.setBuilding(buildingRepository.findById(buildingId).get());
                            room.setCategory(roomCategoryRepository.findById(categoryId).get());
                            room.setStatus(roomStatusRepository.findById(statusId).get());
                            roomRepository.save(room);
                            return room;
                        }
                    }else{
                        throw new RecordNotFoundException("Id does not exist", statusId);
                    }
                }else{
                    throw new RecordNotFoundException("Id does not exist", categoryId);
                }

            }else{
                throw new RecordNotFoundException("Id does not exist", buildingId);
            }
        }else{
            room.setBuilding(buildingRepository.findById(buildingId).get());
            room.setCategory(roomCategoryRepository.findById(categoryId).get());
            room.setStatus(roomStatusRepository.findById(statusId).get());
            roomRepository.save(room);
            return room;
        }


    }


    public void deleteById(Long roomId, Long buildingId) throws RecordNotFoundException {
        Optional<Room> result = roomRepository.findByIdAndBuildingId(roomId,buildingId);
        if (result.isPresent()) {
            roomRepository.delete(result.get());
        } else {
            throw new RecordNotFoundException("Id does not exist", roomId);
        }
    }
}