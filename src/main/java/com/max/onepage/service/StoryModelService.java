package com.max.onepage.service;

import com.max.onepage.models.Story;
import com.max.onepage.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryModelService implements StoryService{

    private final StorageService storageService;
    private final StoryRepository storyRepository;

    @Autowired
    public StoryModelService(StorageService storageService, StoryRepository storyRepository) {
        this.storageService = storageService;
        this.storyRepository = storyRepository;
    }

    @Override
    public List<Story> allStories() {
        return (List<Story>) storyRepository.findAll();
    }

    @Override
    public Story newStory(Story story) {
        storageService.store(story.getFile());

        story.setFilename(story.getFile().getOriginalFilename());

        return storyRepository.save(story);
    }
}
