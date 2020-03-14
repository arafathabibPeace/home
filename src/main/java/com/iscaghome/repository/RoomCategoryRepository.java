package com.iscaghome.repository;

import com.iscaghome.model.RoomCategory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "roomCategoryRepository")
public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Long> {
}
