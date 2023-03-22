package com.example.joblisting.repository;

import com.example.joblisting.model.JobPost;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JobPostRepository extends MongoRepository<JobPost, String> {
}
