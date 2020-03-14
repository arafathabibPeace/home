package com.iscaghome.repository;

import com.iscaghome.model.Billing;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "billingRepository")
public interface BillingRepository extends JpaRepository<Billing,Long> {
}
