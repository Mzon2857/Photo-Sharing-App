package com.photo.controller;

import com.photo.service.ImageService;
import com.photo.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;
    private final S3Service s3Service;

    @Autowired
    public ImageController(ImageService imageService, S3Service s3Service) {
        this.imageService = imageService;
        this.s3Service = s3Service;
    }

    @PostMapping("/{userId}/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadImage(@PathVariable Long userId, @RequestParam("file") MultipartFile file) throws IOException {
        imageService.createImage(userId, file);
        return "Image uploaded successfully.";
    }

    @GetMapping("/{userId}/get-url")
    public List<String> getImageUrl(@PathVariable Long userId) {
        return imageService.getImageUrlByUserId(userId);
    }
}