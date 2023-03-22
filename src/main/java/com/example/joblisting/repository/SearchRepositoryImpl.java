package com.example.joblisting.repository;

import com.example.joblisting.model.JobPost;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class SearchRepositoryImpl implements SearchRepository{

    @Autowired
    MongoClient mongoClient;

    @Autowired
    MongoConverter mongoConverter;

    @Override
    public List<JobPost> findByText(String text) {

        final List<JobPost> posts = new ArrayList<>();

        MongoDatabase database = mongoClient.getDatabase("shamballa");
        MongoCollection<Document> collection = database.getCollection("jobPost");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("index", "default").append("text",
                        new Document("query", text).append("path", Arrays.asList("desc", "techs", "profile")))),
                        new Document("$sort",
                        new Document("exp", 1L)),
                        new Document("$limit", 5L)));

        result.forEach(document -> posts.add(mongoConverter.read(JobPost.class, document)));
        return posts;
    }
}
