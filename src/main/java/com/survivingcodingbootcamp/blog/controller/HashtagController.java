package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import com.survivingcodingbootcamp.blog.repository.TopicRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hashtags")
public class HashtagController {

    private HashtagRepository hashtagRepo;
    private PostRepository postRepo;

    public HashtagController(HashtagRepository hashtagRepo, PostRepository postRepo) {
        this.postRepo = postRepo;
        this.hashtagRepo = hashtagRepo;
    }
    @GetMapping("/{id}")
    public String displaySingleTopic(@PathVariable long id, Model model) {
        model.addAttribute("hashtag", hashtagRepo.findById(id).get());
        return "single-hashtag-template";
    }
    @GetMapping("/")
    public String displayAllHashTags(Model model) {
        model.addAttribute("hashtags", hashtagRepo.findAll());
        return "all-hashtags-template";
    }
    @PostMapping("/saveHashtag")
    public String saveNewHashtag(@RequestParam long postId, @RequestParam String hashtag){
        Post post = postRepo.findById(postId).get();
        Hashtag hashtag1 = new Hashtag(hashtag);
        post.addHashtag(hashtag1);
        hashtagRepo.save(hashtag1);
        postRepo.save(post);
//        return "hashtags/";
        return "redirect:/posts/"+postId;
    }

//    @RequestMapping("/hashtags/")
//    public String displayAllHashTags2(Model model, @PathVariable String name) {
//        model.addAttribute("hashtag", hashtagRepo.findAll());
//        return "all-hashtags-template";
//    }
}
