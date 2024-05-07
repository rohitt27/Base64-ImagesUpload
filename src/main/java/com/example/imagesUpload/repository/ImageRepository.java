package com.example.imagesUpload.repository;

import com.example.imagesUpload.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {

}
