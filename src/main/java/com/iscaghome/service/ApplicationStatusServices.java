package com.iscaghome.service;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.ApplicationStatus;
import com.iscaghome.repository.ApplicationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationStatusServices {

    @Autowired
    ApplicationStatusRepository applicationStatusRepository;

    public List<ApplicationStatus> retrieveAll(){
        List<ApplicationStatus> result = applicationStatusRepository.findAll();
        if(result.size()>0){
            return result;
        }else{
            return new ArrayList<ApplicationStatus>();
        }
    }
    public ApplicationStatus retrieveById(Long id) throws RecordNotFoundException {
        Optional<ApplicationStatus> result = applicationStatusRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
    public ApplicationStatus createOrUpdate(ApplicationStatus applicationStatus)throws RecordNotFoundException{
        if(applicationStatus.getId()!=null){
            Optional<ApplicationStatus> result = applicationStatusRepository.findById(applicationStatus.getId());
            if(result.isPresent()){
                ApplicationStatus newBilling = result.get();
                newBilling.setLabel(applicationStatus.getLabel());
                newBilling.setDescription(applicationStatus.getDescription());

                return newBilling;
            }else{
                applicationStatus = applicationStatusRepository.save(applicationStatus);
                return applicationStatus;
            }
        }else{
            applicationStatus = applicationStatusRepository.save(applicationStatus);
            return applicationStatus;
        }
    }
    public void deleteById(Long id)throws RecordNotFoundException{
        Optional<ApplicationStatus> result = applicationStatusRepository.findById(id);
        if(result.isPresent()){
            applicationStatusRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
}
