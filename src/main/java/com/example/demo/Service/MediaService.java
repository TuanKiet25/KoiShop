package com.example.demo.Service;

import com.example.demo.Entity.Media;
import com.example.demo.model.request.MediaRequest;
import com.example.demo.repository.Mediarepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService
{
    @Autowired
    Mediarepository mediarepository;

    //create
    public Media createMedia (MediaRequest mediaRequest)
    {
        Media media = new Media();
        media.setUrl(mediaRequest.getUrl());
        return mediarepository.save(media);
    }

    //read
    public List<Media> getAllMedia()
    {
        return mediarepository.findAll();
    }

    //find
    public Media findMediaByUrl(MediaRequest mediaRequest)
    {
        return mediarepository.findByUrl(mediaRequest.getUrl());
    }
}
