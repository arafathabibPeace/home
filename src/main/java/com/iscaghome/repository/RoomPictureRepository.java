package com.iscaghome.repository;

import com.iscaghome.model.RoomPicture;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier(value = "roomPictureRepository")
public interface RoomPictureRepository extends JpaRepository<RoomPicture,Long> {
    List<RoomPicture> findByRoomId(Long id);
}
