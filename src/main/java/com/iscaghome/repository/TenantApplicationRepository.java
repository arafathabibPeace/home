package com.iscaghome.repository;

import com.iscaghome.model.TenantApplication;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "tenantApplicationRepository")
public interface TenantApplicationRepository extends JpaRepository<TenantApplication,Long> {
}
