package org.champsoft.likeaholic.businessLogicLayer;


import org.champsoft.likeaholic.dataAccessLayer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likesRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public LikesServiceImpl(LikesRepository likesRepository, PostRepository postRepository, UserRepository userRepository) {
        this.likesRepository = likesRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    @Override
    public void addLike(String userId, String postId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));


        if (likesRepository.existsByUserAndPost(user, post)) {
            throw new IllegalArgumentException("User has already liked this post");
        }


        Likes like = new Likes(user, post);
        likesRepository.save(like);
    }


    @Transactional
    @Override
    public void removeLike(String userId, String postId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));


        Likes like = likesRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new IllegalArgumentException("Like not found"));

        likesRepository.delete(like);
    }


    @Override
    public boolean isLiked(String userId, String postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        return likesRepository.existsByUserAndPost(user, post);
    }

    }