package org.champsoft.likeaholic.businessLogicLayer;



public interface LikesService {

    void addLike(String userId, String postId);

    void removeLike(String userId, String postId);

    boolean isLiked(String userId, String postId);


}