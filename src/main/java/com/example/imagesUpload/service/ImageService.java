package com.example.imagesUpload.service;

import com.example.imagesUpload.entity.Image;
import com.example.imagesUpload.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {
    private static final String UPLOAD_DIRECTORY = "F:\\images\\";
    @Autowired
    private ImageRepository imageRepository;
    public String saveImagePath(MultipartFile file) throws IOException {
        String fileName= file.getOriginalFilename();
        String filePath = UPLOAD_DIRECTORY + fileName;
        Path uploadPath = Paths.get(filePath);
        Files.write(uploadPath,file.getBytes());

        Image image = new Image();
        image.setImagePath(filePath);
        imageRepository.save(image);
        return filePath;
    }
}
