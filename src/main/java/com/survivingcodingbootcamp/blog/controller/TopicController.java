package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import com.survivingcodingbootcamp.blog.repository.TopicRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/topics")
public class TopicController {

    private TopicRepository topicRepo;
    private PostRepository postRepo;

    public TopicController(TopicRepository topicRepo, PostRepository postRepo) {
        this.topicRepo = topicRepo;
        this.postRepo = postRepo;
    }



    @GetMapping("/{id}")
    public String displaySingleTopic(@PathVariable long id, Model model) {
        model.addAttribute("topic", topicRepo.findById(id).get());
        return "single-topic-template";
    }
    @PostMapping("/saveNewPost")
    public String saveNewPost(@RequestParam long topicId, @RequestParam String content, @RequestParam String author, @RequestParam String title){
        Topic topic = topicRepo.findById(topicId).get();
        Post post1 = new Post(title,topic,author,content);
        postRepo.save(post1);

        return "redirect:/topics/"+ topicId;
    }
}
