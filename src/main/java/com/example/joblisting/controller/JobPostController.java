package com.example.joblisting.controller;

import com.example.joblisting.model.JobPost;
import com.example.joblisting.repository.JobPostRepository;
import com.example.joblisting.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobPostController {

    @Autowired
    JobPostRepository jobPostRepository;

    @Autowired
    SearchRepository searchRepository;

    @GetMapping("/posts")
    public List<JobPost> getAllJobPosts(){

        return jobPostRepository.findAll();
    }

    @PostMapping("/post/")
    public JobPost addPost(@RequestBody JobPost jobPost){

        return jobPostRepository.save(jobPost);
    }

    @GetMapping("/posts/{text}")
    public List<JobPost> search(@PathVariable String text){
        return searchRepository.findByText(text);
    }

}
