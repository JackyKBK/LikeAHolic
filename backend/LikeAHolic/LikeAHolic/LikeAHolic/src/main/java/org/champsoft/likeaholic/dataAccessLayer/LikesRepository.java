package org.champsoft.likeaholic.dataAccessLayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> { // <--- Changed String to Long

    boolean existsByUserAndPost(User user, Post post);

    Optional<Likes> findByUserAndPost(User user, Post post);

    long countByPost(Post post);
}