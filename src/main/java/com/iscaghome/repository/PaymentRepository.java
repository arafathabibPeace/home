package com.iscaghome.repository;

import com.iscaghome.model.Payment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "paymentRepository")
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
