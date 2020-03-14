package com.iscaghome.service;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.Tenant;
import com.iscaghome.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TenantServices {

    @Autowired
    TenantRepository tenantRepository;

    public List<Tenant> retrieveAll(){
        List<Tenant> result = tenantRepository.findAll();
        if(result.size()>0){
            return result;
        }else{
            return new ArrayList<Tenant>();
        }
    }
    public Tenant retrieveById(Long id) throws RecordNotFoundException {
        Optional<Tenant> result = tenantRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
    public Tenant createOrUpdate(Tenant tenant)throws RecordNotFoundException{
        if(tenant.getId()!=null){
            Optional<Tenant> result = tenantRepository.findById(tenant.getId());
            if(result.isPresent()){
                Tenant newTenant = result.get();
                newTenant.setTenantNumber(tenant.getTenantNumber());
                newTenant.setPersonId(tenant.getPersonId());

                return newTenant;
            }else{
                tenant = tenantRepository.save(tenant);
                return tenant;
            }
        }else{
            tenant = tenantRepository.save(tenant);
            return tenant;
        }
    }
    public void deleteById(Long id)throws RecordNotFoundException{
        Optional<Tenant> result = tenantRepository.findById(id);
        if(result.isPresent()){
            tenantRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
}
