package com.iscaghome.repository;

import com.iscaghome.model.AdvancePayment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "advancePaymentRepository")
public interface AdvancePaymentRepository extends JpaRepository<AdvancePayment, Long> {
}
