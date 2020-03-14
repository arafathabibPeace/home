package com.iscaghome.service;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.AdvancePayment;
import com.iscaghome.repository.AdvancePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdvancePaymentServices {

    @Autowired
    AdvancePaymentRepository advancePaymentRepository;

    public List<AdvancePayment> retrieveAll(){
        List<AdvancePayment> result = advancePaymentRepository.findAll();
        if(result.size()>0){
            return result;
        }else{
            return new ArrayList<AdvancePayment>();
        }
    }
    public AdvancePayment retrieveById(Long id) throws RecordNotFoundException {
        Optional<AdvancePayment> result = advancePaymentRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
    public AdvancePayment createOrUpdate(AdvancePayment advancePayment)throws RecordNotFoundException{
        if(advancePayment.getId()!=null){
            Optional<AdvancePayment> result = advancePaymentRepository.findById(advancePayment.getId());
            if(result.isPresent()){
                AdvancePayment newAdvancePayment = result.get();
                newAdvancePayment.setBalance(advancePayment.getBalance());
                newAdvancePayment.setDateAdded(advancePayment.getDateAdded());
                newAdvancePayment.setDateLastModified(advancePayment.getDateLastModified());

                return newAdvancePayment;
            }else{
                advancePayment = advancePaymentRepository.save(advancePayment);
                return advancePayment;
            }
        }else{
            advancePayment = advancePaymentRepository.save(advancePayment);
            return advancePayment;
        }
    }
    public void deleteById(Long id)throws RecordNotFoundException{
        Optional<AdvancePayment> result = advancePaymentRepository.findById(id);
        if(result.isPresent()){
            advancePaymentRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
}
