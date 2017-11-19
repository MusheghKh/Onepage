package com.max.onepage.service;

import com.max.onepage.models.Story;

import java.util.List;

public interface StoryService {

    List<Story> allStories();

    Story newStory(Story story);
}
