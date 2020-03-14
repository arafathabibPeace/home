package com.iscaghome.repository;

import com.iscaghome.model.Room;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier(value = "roomRepository")
public interface RoomRepository extends JpaRepository<Room,Long> {

    List<Room> findByBuildingId(Long id);
    Optional<Room> findByIdAndBuildingId(Long id, Long buildingId);

    List<Room> findByBuildingIdAndCategoryId(Long buildingId, Long categoryId);

    List<Room> findByBuildingIdAndStatusId(Long buildingId, Long categoryId);

    List<Room> findByBuildingIdAndCategoryIdAndStatusId(Long buildingId, Long categoryId, Long statusId);


}
