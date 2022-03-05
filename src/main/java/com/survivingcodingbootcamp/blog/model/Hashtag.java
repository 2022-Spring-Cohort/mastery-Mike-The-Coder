package com.survivingcodingbootcamp.blog.model;

import com.survivingcodingbootcamp.blog.model.Topic;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Hashtag {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "hashtags")
    Collection<Post> posts;

    public Hashtag(String name) {
        this.name = name;
        this.posts = new ArrayList<>();
    }

    public Hashtag() {

    }

    public void addPost(Post post) { posts.add(post); }

    public Long getId() { return id; }

    public String getName() { return name; }

    public Collection<Post> getPost() { return posts; }

    @Override
    public String toString() {
        return "Hashtag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hashtag hashtag = (Hashtag) o;

        if (id != null ? !id.equals(hashtag.id) : hashtag.id != null) return false;
        return name != null ? name.equals(hashtag.name) : hashtag.name == null;
    }
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public void add(Hashtag hashtag) {
    }
}
