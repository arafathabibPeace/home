package com.iscaghome.service;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.Payment;
import com.iscaghome.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServices {

    @Autowired
    PaymentRepository paymentRepository;

    public List<Payment> retrieveAll(){
        List<Payment> result = paymentRepository.findAll();
        if(result.size()>0){
            return result;
        }else{
            return new ArrayList<Payment>();
        }
    }
    public Payment retrieveById(Long id) throws RecordNotFoundException {
        Optional<Payment> result = paymentRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
    public Payment createOrUpdate(Payment payment)throws RecordNotFoundException{
        if(payment.getId()!=null){
            Optional<Payment> result = paymentRepository.findById(payment.getId());
            if(result.isPresent()){
                Payment newPayment = result.get();
                newPayment.setAmount(payment.getAmount());
                newPayment.setDatePaid(payment.getDatePaid());
                newPayment.setAdvancePaymentId(payment.getAdvancePaymentId());

                return newPayment;
            }else{
                payment = paymentRepository.save(payment);
                return payment;
            }
        }else{
            payment = paymentRepository.save(payment);
            return payment;
        }
    }
    public void deleteById(Long id)throws RecordNotFoundException{
        Optional<Payment> result = paymentRepository.findById(id);
        if(result.isPresent()){
            paymentRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
}
