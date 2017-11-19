package com.max.onepage.validation;

import com.max.onepage.exception.ValidationException;
import com.max.onepage.models.Story;
import com.max.onepage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class StoryValidator implements BaseValidator<Story>{

    private final StorageService storageService;

    @Autowired
    public StoryValidator(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public void validate(Story story) {
        if (story.getFile().isEmpty()){
            throw new ValidationException("File is Empty");
        }
        if (StringUtils.cleanPath(story.getFile().getOriginalFilename()).contains("..")){
            throw new ValidationException("Cannot store file with relative path outside current directory");
        }
        if (storageService.exist(story.getFile().getOriginalFilename())){
            throw new ValidationException("File with this name already exist");
        }

        if(story.getFirstname().length() < 2 || story.getFirstname().length() > 32){
            throw new ValidationException("First Name must be between 2 and 32 characters");
        }
        if(story.getLastname().length() < 2 || story.getLastname().length() > 32){
            throw new ValidationException("Last Name must be between 2 and 32 characters");
        }
        if(story.getLanguage().length() < 1 || story.getLanguage().length() > 32){
            throw new ValidationException("Language must be between 1 and 32 characters");
        }
        if (story.getOld() < 4 || story.getOld() > 130){
            throw new ValidationException("Old must be between 4 and 130 characters");
        }
    }
}
