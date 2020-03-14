package com.iscaghome.repository;

import com.iscaghome.model.PaymentType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "paymentTypeRepository")
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {


}
