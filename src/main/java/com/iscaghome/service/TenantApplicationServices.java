package com.iscaghome.service;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.TenantApplication;
import com.iscaghome.repository.TenantApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TenantApplicationServices {

    @Autowired
    TenantApplicationRepository tenantApplicationRepository;

    public List<TenantApplication> retrieveAll(){
        List<TenantApplication> result = tenantApplicationRepository.findAll();
        if(result.size()>0){
            return result;
        }else{
            return new ArrayList<TenantApplication>();
        }
    }
    public TenantApplication retrieveById(Long id) throws RecordNotFoundException {
        Optional<TenantApplication> result = tenantApplicationRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
    public TenantApplication createOrUpdate(TenantApplication tenantApplication)throws RecordNotFoundException{
        if(tenantApplication.getId()!=null){
            Optional<TenantApplication> result = tenantApplicationRepository.findById(tenantApplication.getId());
            if(result.isPresent()){
                TenantApplication newTenantApplication = result.get();
                newTenantApplication.setDateApplied(tenantApplication.getDateApplied());
                newTenantApplication.setDateApproved(tenantApplication.getDateApproved());
                newTenantApplication.setTenantId(tenantApplication.getTenantId());
                newTenantApplication.setRoomId(tenantApplication.getRoomId());
                newTenantApplication.setStatusId(tenantApplication.getStatusId());


                return newTenantApplication;
            }else{
                tenantApplication = tenantApplicationRepository.save(tenantApplication);
                return tenantApplication;
            }
        }else{
            tenantApplication = tenantApplicationRepository.save(tenantApplication);
            return tenantApplication;
        }
    }
    public void deleteById(Long id)throws RecordNotFoundException{
        Optional<TenantApplication> result = tenantApplicationRepository.findById(id);
        if(result.isPresent()){
            tenantApplicationRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
}
