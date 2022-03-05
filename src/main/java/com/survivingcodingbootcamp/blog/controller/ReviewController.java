package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Review;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.ReviewRepository;
import com.survivingcodingbootcamp.blog.repository.TopicRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private ReviewRepository reviewRepo;

    public ReviewController(ReviewRepository reviewRepo) {

        this.reviewRepo = reviewRepo;
    }
    @GetMapping("/{id}")
    public String displaySingleTopic(@PathVariable long id, Model model) {
        model.addAttribute("hashtag", reviewRepo.findById(id).get());
        return "single-hashtag-template";
    }
    @GetMapping("/")
    public String displayAllHashTags(Model model) {
        model.addAttribute("review", reviewRepo.findAll());
        return "all-reviews-template";
    }

//    @PostMapping("/saveReview")
//    public String saveReview(@RequestParam long postId, @RequestParam String review){
//        Post post = RERepo.findById(postId).get();
//        Review review1 = new Review(review);
//        review1.addPost(post);
//        reviewRepo.save(review1);
////        return "hashtags/";
//        return "redirect:/posts/"+postId;
//    }
//    @RequestMapping("/hashtags")
//    public String displayAllHashTags2(Model model) {
//        model.addAttribute("hashtag", hashtagRepo.findAll());
//        return "all-hashtags-template";
//    }
}
