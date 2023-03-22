package com.example.joblisting.repository;

import com.example.joblisting.model.JobPost;

import java.util.List;

public interface SearchRepository {

    List<JobPost> findByText(String text);
}
