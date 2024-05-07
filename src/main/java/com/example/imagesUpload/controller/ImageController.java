package com.example.imagesUpload.controller;

import com.example.imagesUpload.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestPart MultipartFile file){
        try {
           String filePath = imageService.saveImagePath(file);
            return "success" + filePath;
        } catch (Exception e) {
            return "failure";
        }
    }
    @GetMapping("/view/{imageName}")
    public ResponseEntity<?> viewImage(@PathVariable String imageName) {
        try {
            // Correcting the path separator and adding file extension if necessary
            Path imagePath = Paths.get("F:\\images", imageName);
            byte[] imageData = Files.readAllBytes(imagePath);
            String base64Image = Base64.getEncoder().encodeToString(imageData);
            // Return Base64 string in the response
            return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(base64Image);

        } catch (NoSuchFileException e) {
            // Handle file not found exception
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            // Handle other IO exceptions
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
