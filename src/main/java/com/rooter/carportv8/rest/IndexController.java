package com.rooter.carportv8.rest;

import com.rooter.carportv8.resources.IndexResources;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("index")
@CrossOrigin(origins = "http://localhost:3000")
public class IndexController{
    @GetMapping
    public IndexResources index(){
        return new IndexResources();
    }
}
