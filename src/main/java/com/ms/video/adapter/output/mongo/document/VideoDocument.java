package com.ms.video.adapter.output.mongo.document;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "videos")
public class VideoDocument {

    @Id
    private String id;

    private String title;
    private String url;

    protected VideoDocument() {}

    public VideoDocument(String title, String url) {
        this.title = title;
        this.url = url;
    }

    // Getters e setters omitidos para brevidade
}