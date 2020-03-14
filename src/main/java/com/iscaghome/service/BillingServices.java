package com.iscaghome.service;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.Billing;
import com.iscaghome.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillingServices {

    @Autowired
    BillingRepository billingRepository;

    public List<Billing> retrieveAll(){
        List<Billing> result = billingRepository.findAll();
        if(result.size()>0){
            return result;
        }else{
            return new ArrayList<Billing>();
        }
    }
    public Billing retrieveById(Long id) throws RecordNotFoundException {
        Optional<Billing> result = billingRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
    public Billing createOrUpdate(Billing billing)throws RecordNotFoundException{
        if(billing.getId()!=null){
            Optional<Billing> result = billingRepository.findById(billing.getId());
            if(result.isPresent()){
                Billing newBilling = result.get();
                newBilling.setBillingDate(billing.getBillingDate());
                newBilling.setDueDate(billing.getDueDate());
                newBilling.setAmount(billing.getAmount());
                newBilling.setTenantId(billing.getTenantId());
                newBilling.setRoomId(billing.getRoomId());
                newBilling.setTenantApplicationId(billing.getTenantApplicationId());
                return newBilling;
            }else{
                billing = billingRepository.save(billing);
                return billing;
            }
        }else{
            billing = billingRepository.save(billing);
            return billing;
        }
    }
    public void deleteById(Long id)throws RecordNotFoundException{
        Optional<Billing> result = billingRepository.findById(id);
        if(result.isPresent()){
            billingRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
}
