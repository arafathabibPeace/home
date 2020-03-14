package com.iscaghome.repository;

import com.iscaghome.model.Tenant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "tenantRepository")
public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
