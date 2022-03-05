package com.survivingcodingbootcamp.blog.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    String words;
    @ManyToOne
    Post post;

    public Review(String words, Post post){
        this.words = words;
        this.post = post;
    }


    public Review() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Post getPost() {
        return post;
    }
}
