package com.backend.springtok.controller;

import com.backend.springtok.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

    private VideoRepository videoRepository;

    @Autowired
    public void setVideoRepository(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

}
