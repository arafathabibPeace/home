package com.iscaghome.repository;

import com.iscaghome.model.RoomStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "roomStatusRepository")
public interface RoomStatusRepository extends JpaRepository<RoomStatus, Long> {
}
