package com.iscaghome.service;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.PaymentType;
import com.iscaghome.repository.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentTypeServices {

    @Autowired
    PaymentTypeRepository paymentTypeRepository;

    public List<PaymentType> retrieveAll(){
        List<PaymentType> result = paymentTypeRepository.findAll();
        if(result.size()>0){
            return result;
        }else{
            return new ArrayList<PaymentType>();
        }
    }
    public PaymentType retrieveById(Long id) throws RecordNotFoundException {
        Optional<PaymentType> result = paymentTypeRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
    public PaymentType createOrUpdate(PaymentType paymentType)throws RecordNotFoundException{
        if(paymentType.getId()!=null){
            Optional<PaymentType> result = paymentTypeRepository.findById(paymentType.getId());
            if(result.isPresent()){
                PaymentType newPaymentType = result.get();
                newPaymentType.setName(paymentType.getName());
                newPaymentType.setDescription(paymentType.getDescription());

                return newPaymentType;
            }else{
                paymentType = paymentTypeRepository.save(paymentType);
                return paymentType;
            }
        }else{
            paymentType = paymentTypeRepository.save(paymentType);
            return paymentType;
        }
    }
    public void deleteById(Long id)throws RecordNotFoundException{
        Optional<PaymentType> result = paymentTypeRepository.findById(id);
        if(result.isPresent()){
            paymentTypeRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
}
