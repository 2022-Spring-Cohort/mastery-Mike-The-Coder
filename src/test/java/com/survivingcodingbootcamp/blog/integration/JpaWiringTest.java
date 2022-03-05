package com.survivingcodingbootcamp.blog.integration;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Review;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import com.survivingcodingbootcamp.blog.repository.ReviewRepository;
import com.survivingcodingbootcamp.blog.repository.TopicRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {
    @Autowired
    private ReviewRepository reviewRepo;
    @Autowired
    private HashtagRepository hashtagRepo;
    @Autowired
    private TopicRepository topicRepo;
    @Autowired
    PostRepository postRepo;
    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void postsShouldHaveATopic() {
        Topic testTopic = new Topic("Name");
        topicRepo.save(testTopic);

        Post testPost1 = new Post("Title", testTopic, "Content", hashtag1, review1);
        postRepo.save(testPost1);
        Post testPost2 = new Post("Another Title", testTopic, "Content", hashtag1, review1);
        postRepo.save(testPost2);

        entityManager.flush();
        entityManager.clear();

        Topic retrievedTopic = topicRepo.findById(testTopic.getId()).get();
        Review retrievedReview = reviewRepo.findById(testReview.getId()).get();
        Hashtag retrievedHashtag = hashtagRepo.findById(hashtag1.getId()).get();

        assertThat(retrievedTopic.getPosts()).containsExactlyInAnyOrder(testPost1, testPost2);
        assertThat(retrievedReview.getPosts()).containsExactlyInAnyOrder(testReview1);
        assertThat(retrievedTopic.getPosts()).containsExactlyInAnyOrder(testPost1, testPost2);

    }
}
