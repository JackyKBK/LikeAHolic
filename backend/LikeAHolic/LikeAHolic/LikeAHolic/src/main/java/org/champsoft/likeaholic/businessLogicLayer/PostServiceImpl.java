package org.champsoft.likeaholic.businessLogicLayer;

import jakarta.transaction.Transactional;
import org.champsoft.likeaholic.dataAccessLayer.*;
import org.champsoft.likeaholic.presentationLayer.PostRequestModel;
import org.champsoft.likeaholic.presentationLayer.PostResponseModel;
import org.champsoft.likeaholic.utilities.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, LikesRepository likesRepository) {
        this.postRepository = postRepository;

        this.userRepository = userRepository;
        this.likesRepository = likesRepository;
    }

    @Override
    public List<PostResponseModel> getAllPosts() {

        List<Post> posts = postRepository.findAll();


        List<PostResponseModel> response = new ArrayList<>();


        for (Post post : posts) {
            PostResponseModel dto = new PostResponseModel();
            BeanUtils.copyProperties(post, dto);
            response.add(dto);
        }


        return response;
    }

    @Override
    public PostResponseModel getPostByPostId(String post_id) {

        Post post = postRepository.findPostByPostId(post_id);


        if (post == null) {
            throw new NotFoundException("Post with post_id " + post_id + " not found.");
        }


        PostResponseModel dto = new PostResponseModel();


        BeanUtils.copyProperties(post, dto);


        return dto;
    }

    @Override
    public String addPost(PostRequestModel postData) {
        String postId = postData.getPostId();


        if (postRepository.findPostByPostId(postId) != null) {
            return "Post with post_id: " + postId + " already exists.";
        }


        Post post = new Post();
        post.setPostId(postData.getPostId());
        post.setTitle(postData.getTitle());
        post.setContent(postData.getContent());
        post.setImageUrl(postData.getImageUrl());
        post.setCreatedAt(postData.getCreatedAt());

        if (postData.getUserId() != null && !postData.getUserId().isEmpty()) {
            User user = userRepository.getByUserId(postData.getUserId());
            post.setUserId(user.getUserId());
        }
        postRepository.save(post);

        return "Post saved successfully.";
    }

    @Override
    public String updatePost(String postId, PostRequestModel postRequestModel) {

        Post foundPost = postRepository.findPostByPostId(postId);
        if (foundPost == null) {
            throw new NotFoundException("Post with post_id: " + postId + " not found.");
        }


        foundPost.setPostId(postRequestModel.getPostId());
        foundPost.setTitle(postRequestModel.getTitle());
        foundPost.setContent(postRequestModel.getContent());
        foundPost.setImageUrl(postRequestModel.getImageUrl());
        foundPost.setCreatedAt(postRequestModel.getCreatedAt());

        if (postRequestModel.getUserId() != null && !postRequestModel.getUserId().isEmpty()) {
            User user = userRepository.getByUserId(postRequestModel.getUserId());
            foundPost.setUserId(user.getUserId());
        }

        Post updatedPost = postRepository.save(foundPost);
        return updatedPost != null ? "Post updated successfully." : "Could not update post.";
    }

    @Override
    public String deletePost(String postId) {

        Post foundPost = postRepository.findPostByPostId(postId);
        if (foundPost == null) {
            throw new NotFoundException("Post with post_id: " + postId + " not found.");
        }


        postRepository.delete(foundPost);
        return "Post with post_id: " + postId + " deleted successfully.";
    }

    @Transactional
    public int toggleLike(Long postId, String userId) {
        User user = userRepository.getByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not found: " + userId);
        }

        Post post = postRepository.findPostByPostId(String.valueOf(postId));
        if (post == null) {
            throw new RuntimeException("Post not found: " + postId);
        }
        Optional<Likes> existingLike = likesRepository.findByUserAndPost(user, post);

        if (existingLike.isPresent()) {
            likesRepository.delete(existingLike.get());
        } else {
            Likes newLike = new Likes(user, post);
            likesRepository.save(newLike);
        }

        return (int) likesRepository.countByPost(post);
    }
}