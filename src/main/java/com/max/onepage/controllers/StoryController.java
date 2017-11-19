package com.max.onepage.controllers;

import com.max.onepage.models.Story;
import com.max.onepage.service.StorageService;
import com.max.onepage.service.StoryService;
import com.max.onepage.validation.StoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stories")
public class StoryController {

    private final StoryValidator storyValidator;
    private final StoryService storyService;
    private final StorageService storageService;

    @Autowired
    public StoryController(StoryValidator storyValidator, StoryService storyService, StorageService storageService) {
        this.storyValidator = storyValidator;
        this.storyService = storyService;
        this.storageService = storageService;
    }

    @GetMapping(value = "/")
    public List<Story> getStories(){
        return storyService.allStories();
    }

    @GetMapping(value = "/files/{filename}.{extension}")
    public ResponseEntity<Resource> getFile(@PathVariable("filename") String filename, @PathVariable("extension") String extension){
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "attachment; filename=" + filename + '.' + extension);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(storageService.loadAsResource(filename + '.' + extension));
    }
}
