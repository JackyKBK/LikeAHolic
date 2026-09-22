package org.champsoft.likeaholic.dataAccessLayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, String> {
    Optional<Post> findById(String post_id);
   Post findPostByPostId(String post_id);
}