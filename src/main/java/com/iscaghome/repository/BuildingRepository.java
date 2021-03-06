package com.iscaghome.repository;

import com.iscaghome.model.Building;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "buildingRepository")
public interface BuildingRepository extends JpaRepository<Building,Long> {
}
