package org.champsoft.likeaholic.presentationLayer;

import org.champsoft.likeaholic.businessLogicLayer.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }


    @GetMapping
    public List<PostResponseModel> getAllPosts() {
        return this.postService.getAllPosts();
    }


    @GetMapping("/{post_id}")
    public PostResponseModel getPostByPostId(@PathVariable String post_id) {
        return this.postService.getPostByPostId(post_id);
    }


    @PostMapping
    public String addPost(@RequestBody PostRequestModel newPostData) {

        return this.postService.addPost(newPostData);
    }


    @PutMapping("{post_id}")
    public String updatePost(@PathVariable String post_id, @RequestBody PostRequestModel updatedPostData) {
        return this.postService.updatePost(post_id, updatedPostData);
    }


    @DeleteMapping("{post_id}")
    public String deletePost(@PathVariable String post_id) {

        return this.postService.deletePost(post_id);
    }
}