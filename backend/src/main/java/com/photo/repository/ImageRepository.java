package com.photo.repository;

import com.photo.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("SELECT i.s3ImageUrl FROM Image i WHERE i.user.id = :userId")
    List<String> findImageUrlByUserId(Long userId);

    @Query(value = "SELECT * FROM images ORDER BY id DESC LIMIT :limit", nativeQuery = true)
    List<Image> findImagesWithLimit(@Param("limit") int limit);

    @Query("SELECT i FROM Image i WHERE i.user.id = :userId")
    List<Image> findImagesByUserId(Long userId);

    Image findImageById(Long imageId);
}

