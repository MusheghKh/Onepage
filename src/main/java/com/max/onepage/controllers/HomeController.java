package com.max.onepage.controllers;

import com.max.onepage.models.Story;
import com.max.onepage.service.StoryService;
import com.max.onepage.validation.StoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    private final StoryService storyService;
    private final StoryValidator storyValidator;

    @Autowired
    public HomeController(StoryService storyService, StoryValidator storyValidator) {
        this.storyService = storyService;
        this.storyValidator = storyValidator;
    }

    @GetMapping(value = {"/", "/home"})
    public String home(){
        return "home";
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout){
        if (error != null){
            model.addAttribute("error", "Your username and password is invalid.");
        }

        if (logout != null){
            model.addAttribute("logout", "You have been logged out successfully.");
        }

        return "login";
    }

    @GetMapping(value = "/admin")
    public String admin(Model model){
        model.addAttribute("stories", storyService.allStories());
        return "admin";
    }

    @PostMapping(value = "/newStory")
    public ResponseEntity storeStory(Story story){
        storyValidator.validate(story);

        storyService.newStory(story);

        Map<String, String> json = new HashMap<>();
        json.put("message", "Thank you!!!");
        return ResponseEntity.ok(json);
    }
}
