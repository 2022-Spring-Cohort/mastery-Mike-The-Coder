package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    private PostRepository postRepo;
    private HashtagRepository hashtagRepo;


    public PostController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping("/{id}")
    public String displaySinglePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postRepo.findById(id).get());
        return "single-post-template";
    }
    //hashtag
    @PostMapping("{id}")
    public String addHashtagToPost(@PathVariable long id, @RequestParam String review, @RequestParam String hashtag){

        Post post = postRepo.findById(id).get();
//        post.addHashtag(null);
        postRepo.save(post);

        Optional<Hashtag> hashtag1 = hashtagRepo.findByNameIgnoreCase(hashtag);
        if (hashtag1.isPresent()) {
            hashtag1.get().addPost(post);
            hashtagRepo.save(hashtag1.get());
        } else {
            Hashtag hashtag2 = new Hashtag(hashtag);
            hashtag2.addPost(post);
            hashtagRepo.save(hashtag2);
        }
        //review
//        @PostMapping("{id}")
//        public String addReviewToPost(@PathVariable long id, @RequestParam String review){
//
//            Post post = postRepo.findById(id).get();
////        post.addReview(review);
//            postRepo.save(post);



        return "redirect:/posts/" + "{id}";
    }
}
