package com.iscaghome.repository;

import com.iscaghome.model.ApplicationStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "applicationStatusRepository")
public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus, Long> {
}
