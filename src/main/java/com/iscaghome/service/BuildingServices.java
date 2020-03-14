package com.iscaghome.service;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.Building;
import com.iscaghome.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingServices {

    @Autowired
    BuildingRepository buildingRepository;

    public List<Building> retrieveAll(){
        List<Building> result = buildingRepository.findAll();
        if(result.size()>0){
            return result;
        }else{
            return new ArrayList<Building>();
        }
    }
    public Building retrieveById(Long id) throws RecordNotFoundException {
        Optional<Building> result = buildingRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
    public Building createOrUpdate(Building building)throws RecordNotFoundException{
        if(building.getId()!=null){
            Optional<Building> result = buildingRepository.findById(building.getId());
            if(!result.isPresent()){
                Building newBuilding = result.get();
                newBuilding.setLabel(building.getLabel());
                newBuilding.setLocation(building.getLocation());

                return newBuilding;
            }else{
                building = buildingRepository.save(building);
                return building;
            }
        }else{
            building = buildingRepository.save(building);
            return building;
        }
    }
    public void deleteById(Long id)throws RecordNotFoundException{
        Optional<Building> result = buildingRepository.findById(id);
        if(result.isPresent()){
            buildingRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
}
